package com.kerke3.simple_bank.users.service;


import com.kerke3.simple_bank.common.dto.UserIdRequest;

import com.kerke3.simple_bank.users.dto.*;

public interface UserService {
    UserResponse createUser(UserIdRequest userIdRequest);
    SuccessResponse deactivateUser(UserIdRequest userIdRequest);

}
