package com.kerke3.simple_bank.accounts.repository;


import com.kerke3.simple_bank.accounts.dto.response.UserAccountsResponse;

public interface AccountRepository {

    UserAccountsResponse userAccounts(String userId);
    UserAccountsResponse openAccount(String userId, String accountId);

}
