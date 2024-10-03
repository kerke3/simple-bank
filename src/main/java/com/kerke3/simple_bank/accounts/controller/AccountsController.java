package com.kerke3.simple_bank.accounts.controller;


import com.kerke3.simple_bank.accounts.dto.*;
import com.kerke3.simple_bank.accounts.service.AccountService;
import com.kerke3.simple_bank.common.dto.StandardResponse;
import com.kerke3.simple_bank.common.dto.UserIdRequest;
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
    public ResponseEntity<StandardResponse> openAccount(@RequestBody @Valid UserAccountRequest userAccountRequest){
        return ResponseEntity.ok(new StandardResponse(true, accountService.openAccount(userAccountRequest),null));

    }

    @GetMapping(path = "/accounts")
    public ResponseEntity<StandardResponse> userAccounts(@Valid UserIdRequest userIdRequest){
        return ResponseEntity.ok(new StandardResponse(true, accountService.userAccounts(userIdRequest),null));

    }

}
