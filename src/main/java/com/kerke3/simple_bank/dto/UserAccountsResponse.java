package com.kerke3.simple_bank.dto;


import com.kerke3.simple_bank.model.Account;

import java.util.HashMap;

public record UserAccountsResponse(String userId, HashMap<String, Account> accounts){
}
