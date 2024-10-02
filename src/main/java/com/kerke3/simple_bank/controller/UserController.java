package com.kerke3.simple_bank.controller;

import com.kerke3.simple_bank.dto.*;
import com.kerke3.simple_bank.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl accountServiceImpl;

    @PostMapping(path = "/create")
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserIdRequest userIdRequest){
        return ResponseEntity.ok(accountServiceImpl.createUser(userIdRequest));
    }

    @PostMapping(path = "/deactivate")
    public ResponseEntity<SuccessResponse> deactivateUser(@RequestBody @Valid UserIdRequest userIdRequest) {
            return ResponseEntity.ok(accountServiceImpl.deactivateUser(userIdRequest));
    }

    @PostMapping(path = "/open-account")
    public ResponseEntity<UserAccountsResponse> openAccount(@RequestBody @Valid UserAccountRequest userAccountRequest){
        return ResponseEntity.ok(accountServiceImpl.openAccount(userAccountRequest));

    }

    @GetMapping(path = "/accounts")
    public ResponseEntity<UserAccountsResponse> userAccounts(@RequestBody @Valid UserIdRequest userIdRequest){
        return ResponseEntity.ok(accountServiceImpl.userAccounts(userIdRequest));

    }

    @PostMapping(path = "/deposit")
    public ResponseEntity<TransactionResponse> deposit(@RequestBody @Valid DepositWithdrawRequest depositWithdrawRequest){
        return ResponseEntity.ok(accountServiceImpl.deposit(depositWithdrawRequest));
    }

    @PostMapping(path = "/withdraw")
    public ResponseEntity<TransactionResponse> withdraw(@RequestBody @Valid DepositWithdrawRequest depositWithdrawRequest){
        return ResponseEntity.ok(accountServiceImpl.withdraw(depositWithdrawRequest));
    }

    @PostMapping(path = "/transfer")
    public ResponseEntity<TransactionResponse> transfer(@RequestBody @Valid UserTransferAmountRequest userTransferAmountRequest){
        return ResponseEntity.ok(accountServiceImpl.transfer(userTransferAmountRequest));
    }

    @GetMapping(path = "/transactions")
    public ResponseEntity<UserTransactionsResponse> userTransactions(@RequestBody @Valid UserIdRequest userIdRequest){
        return ResponseEntity.ok(accountServiceImpl.userTransactions(userIdRequest));

    }
}
