package com.kerke3.simple_bank.service;

import com.kerke3.simple_bank.dto.UserAccountRequest;
import com.kerke3.simple_bank.dto.UserAccountsResponse;
import com.kerke3.simple_bank.dto.UserIdRequest;
import com.kerke3.simple_bank.dto.UserResponse;

public interface UserService {
    UserResponse createUser(UserIdRequest userIdRequest);
    String deactivateUser(UserIdRequest userIdRequest) throws Exception;

    UserAccountsResponse userAccounts(UserIdRequest userIdRequest);

    UserAccountsResponse openAccount(UserAccountRequest userAccountRequest);
}
