package com.kerke3.simple_bank.accounts.service;

import com.kerke3.simple_bank.accounts.dto.*;
import com.kerke3.simple_bank.common.dto.UserIdRequest;

public interface AccountService {

    UserAccountsResponse userAccounts(UserIdRequest userIdRequest);

    UserAccountsResponse openAccount(UserAccountRequest userAccountRequest);

}
