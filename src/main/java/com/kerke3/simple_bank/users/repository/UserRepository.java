package com.kerke3.simple_bank.users.repository;


import com.kerke3.simple_bank.users.dto.request.UserResponse;
import com.kerke3.simple_bank.users.dto.response.SuccessResponse;

public interface UserRepository {
    UserResponse createOrGet(String userId);

    SuccessResponse deactivate(String userId);

}
