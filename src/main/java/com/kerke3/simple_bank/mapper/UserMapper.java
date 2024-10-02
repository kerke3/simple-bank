package com.kerke3.simple_bank.mapper;

import com.kerke3.simple_bank.dto.*;
import com.kerke3.simple_bank.dto.layers.TransactionLayer;
import com.kerke3.simple_bank.enums.TransactionType;
import com.kerke3.simple_bank.model.Account;
import com.kerke3.simple_bank.model.Transaction;
import com.kerke3.simple_bank.model.User;

import java.util.ArrayDeque;
import java.util.stream.Collectors;

public class UserMapper {

    public static User mapToUser(String userId){
        return new User(userId);
    }
    public static User mapToUser(String userId, String accountId){
        return new User(userId, accountId);
    }

    public static UserResponse mapToUserResponse(User user){
        return new UserResponse(user.getUserId(), user.getActive(), user.getAccounts());
    }

    public static UserAccountsResponse mapToUserAccountsResponse(User user){
        return  new UserAccountsResponse(user.getUserId(), user.getAccounts());

    }

    public static SuccessResponse mapToSuccessResponse(String message){
        return new SuccessResponse(message);
    }

    public static TransactionResponse mapToDepositWithdrawResponse(String userId, Account account, Transaction transaction){
        TransactionLayer transactionLayer = new TransactionLayer(transaction.getTransactionId(), transaction.getDatetime(), transaction.getAmount(), transaction.getTransactionType(),
                transaction.getSendingUser(),transaction.getReceivingUser(),transaction.getSendingAccount(),transaction.getReceievingAccount());
        return new TransactionResponse(userId,account,transactionLayer);
    }

    public static Account mapToAccount(String accountId) {
        return new Account(accountId);
    }

    public static Transaction mapToTransaction(double amount, TransactionType transactionType, User sendingUser, User receivingUser, Account sendingAccount, Account receievingAccount) {
        return new Transaction(amount,transactionType,sendingUser,receivingUser,sendingAccount,receievingAccount);
    }

    public static UserTransactionsResponse mapToUserTransactionsResponse(User user) {

        ArrayDeque<TransactionLayer> transactionLayerArrayDeque = user.getTransactions().stream().map(transaction ->
                new TransactionLayer(transaction.getTransactionId(), transaction.getDatetime(), transaction.getAmount(), transaction.getTransactionType(),
                        transaction.getSendingUser(), transaction.getReceivingUser(), transaction.getSendingAccount(),
                            transaction.getReceievingAccount())).collect(Collectors.toCollection(ArrayDeque::new));

        return new UserTransactionsResponse(user.getUserId(), user.getAccounts(),transactionLayerArrayDeque);
    }
}
