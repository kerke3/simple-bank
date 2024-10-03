package com.kerke3.simple_bank.transactions.repository;


import com.kerke3.simple_bank.transactions.dto.response.TransactionResponse;
import com.kerke3.simple_bank.transactions.dto.response.UserTransactionsResponse;

public interface TransactionRepository {

    UserTransactionsResponse userTransactions(String userId);

    TransactionResponse deposit(String userId, String accountId, double amount);

    TransactionResponse withdraw(String userId, String accountId, double amount);
    TransactionResponse transfer(String userId, String accountId, String recipientId, String recipientAccountId, double amount);

}
