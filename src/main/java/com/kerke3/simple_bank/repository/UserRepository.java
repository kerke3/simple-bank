/*
    The account repository will act as a data store for all the accounts. We will use to check if an account exists in our records or not.
    Also, to retrieve objects existing accounts from memory
 */
package com.kerke3.simple_bank.repository;

import com.kerke3.simple_bank.dto.*;
import com.kerke3.simple_bank.enums.TransactionType;
import com.kerke3.simple_bank.exceptions.InactiveUserException;
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
    /*
        If a user is trying to open an account, but he has no profile in the system,
        then create a profile and an account with the desired ID
     */
    public UserAccountsResponse userAccounts(String userId){
        // TODO: should a deactivated user see his accounts ???
        if (users.containsKey(userId)){
            return UserMapper.mapToUserAccountsResponse(users.get(userId));
        }else{
            User user = UserMapper.mapToUser(userId);
            users.put(userId, user);
            return UserMapper.mapToUserAccountsResponse(user);
        }
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
        /* If the user already exists, then open a new account,
            if not then create a new user with the account ID provided.
        */
        if (users.containsKey(userId)){
            User user = users.get(userId);
            // Deactivate users should be able to open new accounts
            checkUserActive(user);
            if(!user.getAccounts().containsKey(accountId)){
                Account account = UserMapper.mapToAccount(accountId);
                user.updateAccounts(account);
            }
            return UserMapper.mapToUserAccountsResponse(user);
        }else{
            User user = UserMapper.mapToUser(userId, accountId);
            users.put(userId, user);
            return UserMapper.mapToUserAccountsResponse(user);
        }
    }

    public UserTransactionsResponse userTransactions(String userId){
        if (!users.containsKey(userId)){
            userNotFound(userId);
        }
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
        if (!users.containsKey(userId)){
            userNotFound(userId);
        }
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
        if (!users.containsKey(userId)){
            userNotFound(userId);
        }
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
    - Check if user exists
    - Check if user is active
    - Check if account exists
    - check if account balance is higher than withdraw amount
    - Create Transaction and append to users transactions
    - Deposit amount into account (update account balance)
    */
    public TransactionResponse transfer(String userId, String accountId,String recipientId, String recipientAccountId, double amount){
        if (!users.containsKey(userId)){
            userNotFound(userId);
        }
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
        Utils
     */

    private void checkUserActive(User user){
        if (!user.getActive()){
            throw new InactiveUserException("Your user profile " + user.getUserId() + " is deactivated, please contact support if you wish to reactivate your profile.");
        }
    }

    private void userNotFound(String userId){
        if (!users.containsKey(userId)) {
            throw new UserNotFoundException("Your user profile " + userId + " is not registered, please create a profile first!");
        }
    }

    private void accountNotFound(User user,String accountId){
        if(!user.getAccounts().containsKey(accountId)) {
            throw new UserNotFoundException("Your user profile " + user.getUserId() + " does not have" + accountId + " account, please open the account first.");
        }
    }

    private void checkBalance(String accountId,double balance, double amount) {
        if (amount > balance){
            throw new InvalidTransactionAmountException(String.format("Your %s account current balance is %.2f, it is not sufficient to withdraw %.2f", accountId,balance,amount));
        }
    }

}
