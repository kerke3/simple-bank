package com.kerke3.simple_bank.transactions.enums;

public enum TransactionType {
    DEBIT("Debit"),
    CREDIT("Credit"),
    WITHDRAWAL("Withdrawal"),
    DEPOSIT("Deposit");

    public final String type;

    TransactionType(String type) {
        this.type = type;
    }
    @Override
    public String toString() {
        return this.type;
    }
}
