package com.kerke3.simple_bank.users.service.impl;

import com.kerke3.simple_bank.common.dto.UserIdRequest;
import com.kerke3.simple_bank.users.dto.*;
import com.kerke3.simple_bank.users.repository.UserRepository;
import com.kerke3.simple_bank.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserResponse createUser(UserIdRequest userIdRequest){
        return userRepository.createOrGet(userIdRequest.userId());
    }

    public SuccessResponse deactivateUser(UserIdRequest userIdRequest){
        return userRepository.deactivate(userIdRequest.userId());
    }
}
