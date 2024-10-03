/*
    The account repository will act as a data store for all the accounts. We will use to check if an account exists in our records or not.
    Also, to retrieve objects existing accounts from memory
 */
package com.kerke3.simple_bank.repository;

import com.kerke3.simple_bank.dto.*;
import com.kerke3.simple_bank.enums.TransactionType;
import com.kerke3.simple_bank.exceptions.InactiveUserException;
import com.kerke3.simple_bank.exceptions.InvalidOperationException;
import com.kerke3.simple_bank.exceptions.InvalidTransactionAmountException;
import com.kerke3.simple_bank.exceptions.UserNotFoundException;
import com.kerke3.simple_bank.mapper.UserMapper;
import com.kerke3.simple_bank.model.Account;
import com.kerke3.simple_bank.model.Transaction;
import com.kerke3.simple_bank.model.User;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
@NoArgsConstructor
public class UserRepository {
    /*
        As our application will run on the main thread only, we can use a simple
        HashMap, however we can use ConcurrentHashMap if concurrency is needed and implements locks where needed.
        This will mimic a database transactional isolation.
    */
    private final static HashMap<String, User> users = new HashMap<>();

    public UserResponse createOrGet(String userId){
        if (users.containsKey(userId)){
            return UserMapper.mapToUserResponse(users.get(userId));
        }else{
            User user = UserMapper.mapToUser(userId);
            users.put(userId, user);
            return UserMapper.mapToUserResponse(user);
        }
    }

    public UserAccountsResponse userAccounts(String userId){
        // TODO: should a deactivated user see his accounts ???
        // Check if user exists
        userNotFound(userId);
        return UserMapper.mapToUserAccountsResponse(users.get(userId));
    }

    public SuccessResponse deactivate(String userId) throws UserNotFoundException,InactiveUserException {
        // Check if user exists
        userNotFound(userId);
        User user = users.get(userId);
        user.setActive(false);
        users.put(userId, user);
        return UserMapper.mapToSuccessResponse("Your user profile has been successfully deactivated, We hope to see you again!");
    }

    public UserAccountsResponse openAccount(String userId, String accountId){

        // user does not exist, no need to continue
        userNotFound(userId);
        User user = users.get(userId);
        // Deactivate users should be able to open new accounts
        checkUserActive(user);
        if(!user.getAccounts().containsKey(accountId)){
            Account account = UserMapper.mapToAccount(accountId);
            user.updateAccounts(account);
        }
        return UserMapper.mapToUserAccountsResponse(user);
    }

    public UserTransactionsResponse userTransactions(String userId){
        // user does not exist, no need to continue
        userNotFound(userId);
        return UserMapper.mapToUserTransactionsResponse(users.get(userId));
    }


    /*
        - Check if user exists
        - Check if user is active
        - Check if account exists
        - Create Transaction and append to users transactions
        - Deposit amount into account (update account balance)
     */
    public TransactionResponse deposit(String userId, String accountId, double amount){
        userNotFound(userId);
        User user = users.get(userId);
        // Deactivated user, no need to continue
        checkUserActive(user);
        // Account doesn't exist, no need to continue
        accountNotFound(user,accountId);
        Account account = user.getAccounts().get(accountId);
        Transaction transaction = UserMapper.mapToTransaction(amount, TransactionType.DEPOSIT,user,user,account,account);
        user.addTransactions(transaction);
        account.setBalance(account.getBalance() + amount);
        return UserMapper.mapToDepositWithdrawResponse(userId,account,transaction);
    }

    /*
    - Check if user exists
    - Check if user is active
    - Check if account exists
    - check if account balance is higher than withdraw amount
    - Create Transaction and append to users transactions
    - Deposit amount into account (update account balance)
 */
    public TransactionResponse withdraw(String userId, String accountId, double amount){
        userNotFound(userId);
        User user = users.get(userId);
        // Deactivated user, no need to continue
        checkUserActive(user);
        // Account doesn't exist, no need to continue
        accountNotFound(user,accountId);
        Account account = user.getAccounts().get(accountId);
        // If balance is insufficient, no need to continue
        checkBalance(accountId,account.getBalance(),amount);
        Transaction transaction = UserMapper.mapToTransaction(amount, TransactionType.WITHDRAWAL,user,user,account,account);
        user.addTransactions(transaction);
        account.setBalance(account.getBalance() - amount);
        return UserMapper.mapToDepositWithdrawResponse(userId,account,transaction);
    }

    /*
    - check if valid transfer
    - Check if user exists
    - Check if recipient exists
    - Check if user is active
    - check if recipient is active
    - Check if account exists
    - check if recipient account exists
    - check if account balance is higher than transfer amount
    - Create Transaction and append to users transactions for both users
    - Deposit amount into account (update account balance) for both accounts
    */
    public TransactionResponse transfer(String userId, String accountId,String recipientId, String recipientAccountId, double amount){
        // check if user is sending to himself and to the same account
        checkifValidTransfer(userId,accountId,recipientId,recipientAccountId);
        // check if users exist
        userNotFound(userId);
        recipientNotFound(recipientId);
        // check if users are active
        User user = users.get(userId);
        User recipient = users.get(recipientId);
        checkUserActive(user);
        checkRecipientActive(recipient);
        // check if accounts exist
        accountNotFound(user,accountId);
        if (userId.equals(recipientId)) {
            accountNotFound(user, recipientAccountId);
        } else {
            recipientAccountNotFound(recipient, recipientAccountId);
        }
        // get accounts
        Account account = user.getAccounts().get(accountId);
        Account recipientAccount = recipient.getAccounts().get(recipientAccountId);
        // If balance is insufficient, no need to continue
        checkBalance(accountId,account.getBalance(),amount);
        // process for recipient
        Transaction recipientTransaction = UserMapper.mapToTransaction(amount, TransactionType.CREDIT,user,recipient,account,recipientAccount);
        recipient.addTransactions(recipientTransaction);
        recipientAccount.setBalance(recipientAccount.getBalance() + amount);
        // process for user
        Transaction transaction = UserMapper.mapToTransaction(amount, TransactionType.DEBIT,user,recipient,account,recipientAccount);
        user.addTransactions(transaction);
        account.setBalance(account.getBalance() - amount);
        return UserMapper.mapToDepositWithdrawResponse(userId,account,transaction);
    }

    /*
        Utils
     */

    private void checkUserActive(User user){
        if (!user.getActive()){
            throw new InactiveUserException("Your user profile " + user.getUserId() + " is deactivated, please contact support if you wish to reactivate your profile.");
        }
    }

    private void checkRecipientActive(User recipient){
        if (!recipient.getActive()){
            throw new InactiveUserException("Your recipient profile " + recipient.getUserId() + " is deactivated, Transfers to inactive profiles are not allowed.");
        }
    }

    private void userNotFound(String userId){
        if (!users.containsKey(userId)) {
            throw new UserNotFoundException("Your user profile " + userId + " is not registered, please create a profile first!");
        }
    }
    private void recipientNotFound(String recipientId){
        if (!users.containsKey(recipientId)) {
            throw new UserNotFoundException("Your recipient profile " + recipientId + " does not exist, please refer to the recipient for the correct ID.");
        }
    }

    private void accountNotFound(User user,String accountId){
        if(!user.getAccounts().containsKey(accountId)) {
            throw new UserNotFoundException("Your user profile " + user.getUserId() + " does not have " + accountId + " account, please open the account first.");
        }
    }
    private void recipientAccountNotFound(User recipient,String recipientAccountId){
        if(!recipient.getAccounts().containsKey(recipientAccountId)) {
            throw new UserNotFoundException("Your recipient profile " + recipient.getUserId() + " does not have " + recipientAccountId + " account, please refer to the recipient for the correct account ID.");
        }
    }

    private void checkBalance(String accountId,double balance, double amount) {
        if (amount > balance){
            throw new InvalidTransactionAmountException(String.format("Your %s account current balance is %.2f, it is not sufficient for transaction amount %.2f", accountId,balance,amount));
        }
    }

    private void checkifValidTransfer(String userId, String accountId, String recipientId, String recipientAccountId) {
        if (userId.equals(recipientId) && accountId.equals(recipientAccountId)){
            throw new InvalidOperationException("Am I a joke to you ???");
        }
    }



}
