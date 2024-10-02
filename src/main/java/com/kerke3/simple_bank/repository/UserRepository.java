/*
    The account repository will act as a data store for all the accounts. We will use to check if an account exists in our records or not.
    Also, to retrieve objects existing accounts from memory
 */
package com.kerke3.simple_bank.repository;

import com.kerke3.simple_bank.dto.UserAccountRequest;
import com.kerke3.simple_bank.dto.UserIdRequest;
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
        // TODO: should an de-activated user see his accounts ???
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
    public String deactivate(String accountId) throws Exception{
        if (users.containsKey(accountId)){
            User user = users.get(accountId);
            if(!user.getActive()){
                return "Your account is already in-active, please contact support if you wish to reactivate your account.";
            }
            user.setActive(false);
            users.put(accountId, user);
            return "Your account has been successfully deactivated, We hope to see you again!";
        }else{
            throw new Exception("Your account is not registered, please create an account first!");
        }
    }

}
