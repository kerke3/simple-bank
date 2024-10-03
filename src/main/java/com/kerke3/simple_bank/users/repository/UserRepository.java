package com.kerke3.simple_bank.users.repository;


import com.kerke3.simple_bank.users.dto.*;

public interface UserRepository {
    UserResponse createOrGet(String userId);

    SuccessResponse deactivate(String userId);

}
