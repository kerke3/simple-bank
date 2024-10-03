/*
    The account repository will act as a data store for all the accounts. We will use to check if an account exists in our records or not.
    Also, to retrieve objects existing accounts from memory
 */
package com.kerke3.simple_bank.transactions.repository.impl;


import com.kerke3.simple_bank.accounts.model.Account;
import com.kerke3.simple_bank.transactions.dto.response.TransactionResponse;
import com.kerke3.simple_bank.transactions.dto.response.UserTransactionsResponse;
import com.kerke3.simple_bank.transactions.enums.TransactionType;
import com.kerke3.simple_bank.transactions.mapper.TransactionMapper;
import com.kerke3.simple_bank.transactions.model.Transaction;
import com.kerke3.simple_bank.transactions.repository.TransactionRepository;
import com.kerke3.simple_bank.users.model.User;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.kerke3.simple_bank.common.repository.CommonRepository.*;


@Repository
@NoArgsConstructor
public class TransactionRepositoryImpl implements TransactionRepository {

    public UserTransactionsResponse userTransactions(String userId){
        // user does not exist, no need to continue
        userNotFound(userId);
        User user = users.get(userId);
        // Deactivated user, no need to continue, deactivated users shouldn't see their transactions ???
        checkUserActive(user);
        return TransactionMapper.mapToUserTransactionsResponse(user);
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
        Transaction transaction = TransactionMapper.mapToTransaction(amount, TransactionType.DEPOSIT,user,user,account,account);
        user.addTransactions(transaction);
        account.setBalance(account.getBalance() + amount);
        return TransactionMapper.mapToDepositWithdrawResponse(userId,account,transaction);
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
        Transaction transaction = TransactionMapper.mapToTransaction(amount, TransactionType.WITHDRAWAL,user,user,account,account);
        user.addTransactions(transaction);
        account.setBalance(account.getBalance() - amount);
        return TransactionMapper.mapToDepositWithdrawResponse(userId,account,transaction);
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
    public TransactionResponse transfer(String userId, String accountId, String recipientId, String recipientAccountId, double amount){
        // check if user is sending to himself and to the same account
        checkIfValidTransfer(userId,accountId,recipientId,recipientAccountId);
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
        Transaction recipientTransaction = TransactionMapper.mapToTransaction(amount, TransactionType.CREDIT,user,recipient,account,recipientAccount);
        recipient.addTransactions(recipientTransaction);
        recipientAccount.setBalance(recipientAccount.getBalance() + amount);
        // process for user
        Transaction transaction = TransactionMapper.mapToTransaction(amount, TransactionType.DEBIT,user,recipient,account,recipientAccount);
        user.addTransactions(transaction);
        account.setBalance(account.getBalance() - amount);
        return TransactionMapper.mapToDepositWithdrawResponse(userId,account,transaction);
    }

}
