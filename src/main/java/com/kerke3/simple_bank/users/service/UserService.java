package com.kerke3.simple_bank.users.service;


import com.kerke3.simple_bank.common.dto.request.UserIdRequest;

import com.kerke3.simple_bank.users.dto.request.UserResponse;
import com.kerke3.simple_bank.users.dto.response.SuccessResponse;

public interface UserService {
    UserResponse createUser(UserIdRequest userIdRequest);
    SuccessResponse deactivateUser(UserIdRequest userIdRequest);

}
