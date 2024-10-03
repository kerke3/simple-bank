/*
    The account repository will act as a data store for all the accounts. We will use to check if an account exists in our records or not.
    Also, to retrieve objects existing accounts from memory
 */
package com.kerke3.simple_bank.users.repository.impl;



import com.kerke3.simple_bank.users.dto.*;
import com.kerke3.simple_bank.users.mapper.UserMapper;
import com.kerke3.simple_bank.users.model.User;
import com.kerke3.simple_bank.users.repository.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.kerke3.simple_bank.common.repository.CommonRepository.*;


@Repository
@NoArgsConstructor
public class UserRepositoryImpl implements UserRepository {


    public UserResponse createOrGet(String userId){
        if (users.containsKey(userId)){
            return UserMapper.mapToUserResponse(users.get(userId));
        }else{
            User user = UserMapper.mapToUser(userId);
            users.put(userId, user);
            return UserMapper.mapToUserResponse(user);
        }
    }

    public SuccessResponse deactivate(String userId) {
        // Check if user exists
        userNotFound(userId);
        User user = users.get(userId);
        user.setActive(false);
        users.put(userId, user);
        return UserMapper.mapToSuccessResponse("Your user profile has been successfully deactivated, We hope to see you again!");
    }

}
