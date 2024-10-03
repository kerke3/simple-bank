package com.kerke3.simple_bank.accounts.controller;


import com.kerke3.simple_bank.accounts.dto.response.UserAccountsResponse;
import com.kerke3.simple_bank.accounts.service.AccountService;
import com.kerke3.simple_bank.common.dto.request.UserIdRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountsController {

    private final AccountService accountService;


    @PostMapping(path = "/open-account")
    public ResponseEntity<UserAccountsResponse> openAccount(@RequestBody @Valid UserAccountRequest userAccountRequest){
        return ResponseEntity.ok(accountService.openAccount(userAccountRequest));

    }

    @GetMapping(path = "/accounts")
    public ResponseEntity<UserAccountsResponse> userAccounts(@Valid UserIdRequest userIdRequest){
        return ResponseEntity.ok(accountService.userAccounts(userIdRequest));

    }

}
