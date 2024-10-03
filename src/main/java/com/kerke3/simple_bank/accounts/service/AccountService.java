package com.kerke3.simple_bank.accounts.service;

import com.kerke3.simple_bank.accounts.dto.request.UserAccountRequest;
import com.kerke3.simple_bank.accounts.dto.response.UserAccountsResponse;
import com.kerke3.simple_bank.common.dto.request.UserIdRequest;

public interface AccountService {

    UserAccountsResponse userAccounts(UserIdRequest userIdRequest);

    UserAccountsResponse openAccount(UserAccountRequest userAccountRequest);

}
