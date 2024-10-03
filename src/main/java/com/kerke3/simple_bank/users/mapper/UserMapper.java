package com.kerke3.simple_bank.users.mapper;


import com.kerke3.simple_bank.users.dto.request.UserResponse;
import com.kerke3.simple_bank.users.dto.response.SuccessResponse;
import com.kerke3.simple_bank.users.model.User;


public class UserMapper {

    public static User mapToUser(String userId){
        return new User(userId);
    }

    public static UserResponse mapToUserResponse(User user){
        return new UserResponse(user.getUserId(), user.getActive(), user.getAccounts());
    }

    public static SuccessResponse mapToSuccessResponse(String message){
        return new SuccessResponse(message);
    }

}
