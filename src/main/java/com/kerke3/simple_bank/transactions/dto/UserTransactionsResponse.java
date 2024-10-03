package com.kerke3.simple_bank.transactions.dto;


import com.kerke3.simple_bank.accounts.model.Account;
import com.kerke3.simple_bank.transactions.dto.layers.TransactionLayer;

import java.util.ArrayDeque;
import java.util.HashMap;

public record UserTransactionsResponse(String userId, HashMap<String, Account> accounts,ArrayDeque<TransactionLayer> transactions){
}
