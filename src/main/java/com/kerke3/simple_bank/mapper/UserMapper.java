package com.kerke3.simple_bank.mapper;

import com.kerke3.simple_bank.dto.*;
import com.kerke3.simple_bank.model.User;

public class UserMapper {

    public static User mapToUser(UserIdRequest userIdRequest){
        return new User(userIdRequest.userId());
    }
    public static User mapToUser(UserAccountRequest userAccountRequest){
        return new User(userAccountRequest.userId(), userAccountRequest.accountId());
    }

    public static UserResponse mapToUserResponse(User user){
        return new UserResponse(user.getUserId(), user.getActive(), user.getAccounts());
    }

    public static UserAccountsResponse mapToUserAccountsResponse(User user){
        return  new UserAccountsResponse(user.getUserId(), user.getAccounts());

    }

    public static SuccessResponse mapToSuccessResponse(String message){
        return new SuccessResponse(message);
    }
}
