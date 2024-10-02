package com.kerke3.simple_bank.mapper;

import com.kerke3.simple_bank.dto.UserAccountRequest;
import com.kerke3.simple_bank.dto.UserAccountsResponse;
import com.kerke3.simple_bank.dto.UserIdRequest;
import com.kerke3.simple_bank.dto.UserResponse;
import com.kerke3.simple_bank.model.User;

public class UserMapper {

    public static User mapToUser(UserIdRequest userIdRequest){
        User user = new User(userIdRequest.userId());
        return user;
    }
    public static User mapToUser(UserAccountRequest userAccountRequest){
        User user = new User(userAccountRequest.userId(), userAccountRequest.accountId());
        return user;
    }

    public static UserResponse mapToUserResponse(User user){
        UserResponse userResponse = new UserResponse(user.getUserId(), user.getActive(), user.getAccounts());
        return userResponse;
    }

    public static UserAccountsResponse mapToUserAccountsResponse(User user){
        UserAccountsResponse userAccountsResponse = new UserAccountsResponse(user.getUserId(), user.getAccounts());
        return userAccountsResponse;
    }
}
