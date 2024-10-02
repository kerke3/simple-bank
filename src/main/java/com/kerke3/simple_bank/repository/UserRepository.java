/*
    The account repository will act as a data store for all the accounts. We will use to check if an account exists in our records or not.
    Also, to retrieve objects existing accounts from memory
 */
package com.kerke3.simple_bank.repository;

import com.kerke3.simple_bank.dto.SuccessResponse;
import com.kerke3.simple_bank.dto.UserAccountRequest;
import com.kerke3.simple_bank.dto.UserIdRequest;
import com.kerke3.simple_bank.exceptions.InactiveUserException;
import com.kerke3.simple_bank.exceptions.UserNotFoundException;
import com.kerke3.simple_bank.mapper.UserMapper;
import com.kerke3.simple_bank.model.Account;
import com.kerke3.simple_bank.model.User;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
@NoArgsConstructor
public class UserRepository {
    /*
        As our application will run on the main thread only, we can use a simple
        HashMap, however we can use ConcurrentHashMap if concurrency is needed and implements locks where needed.
        This will mimic a database transactional isolation.
    */
    private static HashMap<String, User> users = new HashMap<>();

    public User createOrGet(UserIdRequest userIdRequest){
        // TODO: should a deactivated user see his accounts ???
        String userId = userIdRequest.userId();
        if (users.containsKey(userId)){
            return users.get(userId);
        }else{
            User user = UserMapper.mapToUser(userIdRequest);
            users.put(userId, user);
            return user;
        }
    }
    public User openAccount(UserAccountRequest userAccountRequest){
        /* If the user already exists, then open a new account,
            if not then create a new user with the account ID provided.
        */
        String userId = userAccountRequest.userId();
        String accountId = userAccountRequest.accountId();
        if (users.containsKey(userId)){
            User user = users.get(userId);
            // Deactivate users should be able to open new accounts
            checkUserActive(user);
            HashMap<String, Account> userAccounts = user.getAccounts();
            if(!userAccounts.containsKey(accountId)){
                Account account = new Account(accountId);
                userAccounts.put(accountId,account);
            }
            return user;
        }else{
            User user = UserMapper.mapToUser(userAccountRequest);
            users.put(userId, user);
            return user;
        }
    }

    /*
        The messaging strings here are messy, an ENUM can be used or a flag can be just sent back. The approach here is
        to just give clear messaging to the user
     */
    public SuccessResponse deactivate(String userId) throws UserNotFoundException,InactiveUserException {
        if (users.containsKey(userId)){
            User user = users.get(userId);
            if(!user.getActive()){
                throw new InactiveUserException("Your user profile" + userId + " is already deactivated, please contact support if you wish to reactivate your account.");
            }
            user.setActive(false);
            users.put(userId, user);
            return UserMapper.mapToSuccessResponse("Your user profile has been successfully deactivated, We hope to see you again!");

        }else{
            throw new UserNotFoundException("Your user profile " + userId + " is not registered, please create an account first!");
        }
    }

    private void checkUserActive(User user){
        if (!user.getActive()){
            throw new InactiveUserException("Your user profile" + user.getUserId() + " is deactivated, please contact support if you wish to reactivate your account.");
        }
    }

}
