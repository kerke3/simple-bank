package com.kerke3.simple_bank.accounts.service.impl;

import com.kerke3.simple_bank.accounts.dto.request.UserAccountRequest;
import com.kerke3.simple_bank.accounts.dto.response.UserAccountsResponse;
import com.kerke3.simple_bank.accounts.repository.AccountRepository;
import com.kerke3.simple_bank.accounts.service.AccountService;
import com.kerke3.simple_bank.common.dto.request.UserIdRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;


    public UserAccountsResponse openAccount(UserAccountRequest userAccountRequest){
        return accountRepository.openAccount(userAccountRequest.userId(),userAccountRequest.accountId());
    }

    public UserAccountsResponse userAccounts(UserIdRequest userIdRequest){
        return accountRepository.userAccounts(userIdRequest.userId());
    }

}
