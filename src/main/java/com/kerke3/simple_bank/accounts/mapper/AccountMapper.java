package com.kerke3.simple_bank.accounts.mapper;


import com.kerke3.simple_bank.accounts.dto.response.UserAccountsResponse;
import com.kerke3.simple_bank.accounts.model.Account;
import com.kerke3.simple_bank.users.model.User;

public class AccountMapper {


    public static UserAccountsResponse mapToUserAccountsResponse(User user){
        return  new UserAccountsResponse(user.getUserId(), user.getAccounts());

    }

    public static Account mapToAccount(String accountId) {
        return new Account(accountId);
    }



}
