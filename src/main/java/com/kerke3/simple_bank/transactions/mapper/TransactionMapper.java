package com.kerke3.simple_bank.transactions.mapper;


import com.kerke3.simple_bank.accounts.model.Account;
import com.kerke3.simple_bank.transactions.dto.*;
import com.kerke3.simple_bank.transactions.enums.TransactionType;
import com.kerke3.simple_bank.transactions.model.Transaction;
import com.kerke3.simple_bank.transactions.dto.layers.TransactionLayer;
import com.kerke3.simple_bank.users.model.User;


import java.util.ArrayDeque;
import java.util.stream.Collectors;

public class TransactionMapper {

    public static TransactionResponse mapToDepositWithdrawResponse(String userId, Account account, Transaction transaction){
        TransactionLayer transactionLayer = new TransactionLayer(transaction.getTransactionId(), transaction.getDatetime(), transaction.getAmount(), transaction.getTransactionType(),
                transaction.getSendingUser(),transaction.getReceivingUser(),transaction.getSendingAccount(),transaction.getReceievingAccount());
        return new TransactionResponse(userId,account,transactionLayer);
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
