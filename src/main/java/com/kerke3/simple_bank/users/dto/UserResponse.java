package com.kerke3.simple_bank.users.dto;

import com.kerke3.simple_bank.accounts.model.Account;

import java.util.HashMap;


public record UserResponse(String userId, Boolean active, HashMap<String, Account> accounts){
}
