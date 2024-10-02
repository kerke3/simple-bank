package com.kerke3.simple_bank.dto;

import com.kerke3.simple_bank.dto.layers.TransactionLayer;
import com.kerke3.simple_bank.model.Account;

public record TransactionResponse(String userId, Account account, TransactionLayer transaction) {
}
