package com.kerke3.simple_bank.transactions.dto.layers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kerke3.simple_bank.accounts.model.Account;
import com.kerke3.simple_bank.transactions.enums.TransactionType;
import com.kerke3.simple_bank.users.model.User;
import jakarta.annotation.Nullable;
import lombok.Data;

import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionLayer {
    private UUID transactionId;
    private String datetime;
    private double amount;
    private TransactionType transactionType;
    @Nullable
    private String sendingUser;
    @Nullable
    private String receivingUser;
    @Nullable
    private String sendingAccount;
    @Nullable
    private String receievingAccount;

    public TransactionLayer(UUID transactionId, String datetime, double amount, TransactionType transactionType, User sendingUser, User receivingUser, Account sendingAccount, Account receievingAccount) {
        this.transactionId = transactionId;
        this.datetime = datetime;
        this.amount = amount;
        this.transactionType = transactionType;
        switch (transactionType) {
            case DEPOSIT -> {
                this.receivingUser = receivingUser.getUserId();
                this.receievingAccount = receievingAccount.getAccountId();
            }
            case WITHDRAWAL -> {
                this.sendingUser = sendingUser.getUserId();
                this.sendingAccount = sendingAccount.getAccountId();
            }
            case DEBIT, CREDIT -> {
                this.sendingUser = sendingUser.getUserId();
                this.receivingUser = receivingUser.getUserId();
                this.sendingAccount = sendingAccount.getAccountId();
                this.receievingAccount = receievingAccount.getAccountId();
            }
        }
    }
}
