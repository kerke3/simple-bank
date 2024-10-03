package com.kerke3.simple_bank.accounts.dto.response;


import com.kerke3.simple_bank.accounts.model.Account;

import java.util.HashMap;

public record UserAccountsResponse(String userId, HashMap<String, Account> accounts){
}
