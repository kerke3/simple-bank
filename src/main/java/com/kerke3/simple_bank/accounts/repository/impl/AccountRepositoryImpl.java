/*
    The account repository will act as a data store for all the accounts. We will use to check if an account exists in our records or not.
    Also, to retrieve objects existing accounts from memory
 */
package com.kerke3.simple_bank.accounts.repository.impl;


import com.kerke3.simple_bank.accounts.dto.response.UserAccountsResponse;
import com.kerke3.simple_bank.accounts.mapper.AccountMapper;
import com.kerke3.simple_bank.accounts.model.Account;
import com.kerke3.simple_bank.accounts.repository.AccountRepository;
import com.kerke3.simple_bank.users.model.User;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;


import static com.kerke3.simple_bank.common.repository.CommonRepository.*;

@Repository
@NoArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {


    public UserAccountsResponse userAccounts(String userId){
        // TODO: should a deactivated user see his accounts ???
        // Check if user exists
        userNotFound(userId);
        return AccountMapper.mapToUserAccountsResponse(users.get(userId));
    }


    public UserAccountsResponse openAccount(String userId, String accountId){

        // user does not exist, no need to continue
        userNotFound(userId);
        User user = users.get(userId);
        // Deactivate users should be able to open new accounts
        checkUserActive(user);
        if(!user.getAccounts().containsKey(accountId)){
            Account account = AccountMapper.mapToAccount(accountId);
            user.updateAccounts(account);
        }
        return AccountMapper.mapToUserAccountsResponse(user);
    }

}
