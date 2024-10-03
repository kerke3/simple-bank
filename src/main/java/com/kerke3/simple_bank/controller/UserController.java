package com.kerke3.simple_bank.controller;

import com.kerke3.simple_bank.dto.*;
import com.kerke3.simple_bank.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl accountServiceImpl;

    @PostMapping(path = "/create")
    public ResponseEntity<StandardResponse> createUser(@RequestBody @Valid UserIdRequest userIdRequest){
        return ResponseEntity.ok(new StandardResponse(true,accountServiceImpl.createUser(userIdRequest),null));
    }

    @PostMapping(path = "/deactivate")
    public ResponseEntity<StandardResponse> deactivateUser(@RequestBody @Valid UserIdRequest userIdRequest) {
            return ResponseEntity.ok(new StandardResponse(true,accountServiceImpl.deactivateUser(userIdRequest),null));
    }

    @PostMapping(path = "/open-account")
    public ResponseEntity<StandardResponse> openAccount(@RequestBody @Valid UserAccountRequest userAccountRequest){
        return ResponseEntity.ok(new StandardResponse(true,accountServiceImpl.openAccount(userAccountRequest),null));

    }

    @GetMapping(path = "/accounts")
    public ResponseEntity<StandardResponse> userAccounts(@Valid UserIdRequest userIdRequest){
        return ResponseEntity.ok(new StandardResponse(true,accountServiceImpl.userAccounts(userIdRequest),null));

    }

    @PostMapping(path = "/deposit")
    public ResponseEntity<StandardResponse> deposit(@RequestBody @Valid DepositWithdrawRequest depositWithdrawRequest){
        return ResponseEntity.ok(new StandardResponse(true,accountServiceImpl.deposit(depositWithdrawRequest),null));
    }

    @PostMapping(path = "/withdraw")
    public ResponseEntity<StandardResponse> withdraw(@RequestBody @Valid DepositWithdrawRequest depositWithdrawRequest){
        return ResponseEntity.ok(new StandardResponse(true,accountServiceImpl.withdraw(depositWithdrawRequest),null));
    }

    @PostMapping(path = "/transfer")
    public ResponseEntity<StandardResponse> transfer(@RequestBody @Valid UserTransferAmountRequest userTransferAmountRequest){
        return ResponseEntity.ok(new StandardResponse(true,accountServiceImpl.transfer(userTransferAmountRequest),null));
    }

    @GetMapping(path = "/transactions")
    public ResponseEntity<StandardResponse> userTransactions(@Valid UserIdRequest userIdRequest){
        return ResponseEntity.ok(new StandardResponse(true,accountServiceImpl.userTransactions(userIdRequest),null));

    }
}
