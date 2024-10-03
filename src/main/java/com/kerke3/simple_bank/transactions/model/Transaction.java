package com.kerke3.simple_bank.transactions.model;

import com.kerke3.simple_bank.accounts.model.Account;
import com.kerke3.simple_bank.transactions.enums.TransactionType;
import com.kerke3.simple_bank.users.model.User;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class Transaction {
    private UUID transactionId;
    private String datetime;
    private double amount;
    private TransactionType transactionType;
    @Nullable
    private User sendingUser;
    @Nullable
    private User receivingUser;
    @Nullable
    private Account sendingAccount;
    @Nullable
    private Account receievingAccount;


    public Transaction(double amount, TransactionType transactionType, User sendingUser, User receivingUser, Account sendingAccount, Account receievingAccount) {
        this.transactionId = UUID.randomUUID();
        this.datetime = Instant.now().toString();
        this.amount = amount;
        this.transactionType = transactionType;
        switch (transactionType) {
            case DEPOSIT -> {
                this.receivingUser = receivingUser;
                this.receievingAccount = receievingAccount;
            }
            case WITHDRAWAL -> {
                this.sendingUser = sendingUser;
                this.sendingAccount = sendingAccount;
            }
            case DEBIT, CREDIT -> {
                this.sendingUser = sendingUser;
                this.receivingUser = receivingUser;
                this.sendingAccount = sendingAccount;
                this.receievingAccount = receievingAccount;
            }
        }

    }

}
