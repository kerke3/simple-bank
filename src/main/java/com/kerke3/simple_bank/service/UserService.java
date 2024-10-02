package com.kerke3.simple_bank.service;

import com.kerke3.simple_bank.dto.*;
import com.kerke3.simple_bank.exceptions.InactiveUserException;
import com.kerke3.simple_bank.exceptions.UserNotFoundException;

public interface UserService {
    UserResponse createUser(UserIdRequest userIdRequest);
    SuccessResponse deactivateUser(UserIdRequest userIdRequest) throws UserNotFoundException, InactiveUserException;

    UserAccountsResponse userAccounts(UserIdRequest userIdRequest);

    UserAccountsResponse openAccount(UserAccountRequest userAccountRequest);
}
