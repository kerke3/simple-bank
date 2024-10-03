package com.kerke3.simple_bank.dto;


import com.kerke3.simple_bank.dto.layers.TransactionLayer;
import com.kerke3.simple_bank.model.Account;


import java.util.ArrayDeque;
import java.util.HashMap;

public record UserTransactionsResponse(String userId, HashMap<String, Account> accounts,ArrayDeque<TransactionLayer> transactions){
}
