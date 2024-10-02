package com.kerke3.simple_bank.service.impl;

import com.kerke3.simple_bank.dto.UserAccountRequest;
import com.kerke3.simple_bank.dto.UserAccountsResponse;
import com.kerke3.simple_bank.dto.UserIdRequest;
import com.kerke3.simple_bank.dto.UserResponse;
import com.kerke3.simple_bank.mapper.UserMapper;
import com.kerke3.simple_bank.model.User;
import com.kerke3.simple_bank.repository.UserRepository;
import com.kerke3.simple_bank.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserResponse createUser(UserIdRequest userIdRequest){
        User user = userRepository.createOrGet(userIdRequest);
        return UserMapper.mapToUserResponse(user);
    }

    public String deactivateUser(UserIdRequest userIdRequest) throws Exception{
        return userRepository.deactivate(userIdRequest.userId());
    }

    public UserAccountsResponse openAccount(UserAccountRequest userAccountRequest){
        User user = userRepository.openAccount(userAccountRequest);
        return UserMapper.mapToUserAccountsResponse(user);
    }

    public UserAccountsResponse userAccounts(UserIdRequest userIdRequest){
        User user = userRepository.createOrGet(userIdRequest);
        return UserMapper.mapToUserAccountsResponse(user);
    }
}
