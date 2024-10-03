package com.kerke3.simple_bank.transactions.dto.response;

import com.kerke3.simple_bank.accounts.model.Account;
import com.kerke3.simple_bank.transactions.dto.layers.TransactionLayer;

public record TransactionResponse(String userId, Account account, TransactionLayer transaction) {
}
