package com.kerke3.simple_bank.transactions.controller;


import com.kerke3.simple_bank.common.dto.StandardResponse;
import com.kerke3.simple_bank.common.dto.UserIdRequest;
import com.kerke3.simple_bank.transactions.dto.DepositWithdrawRequest;
import com.kerke3.simple_bank.transactions.dto.UserTransferAmountRequest;
import com.kerke3.simple_bank.transactions.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;


    @PostMapping(path = "/deposit")
    public ResponseEntity<StandardResponse> deposit(@RequestBody @Valid DepositWithdrawRequest depositWithdrawRequest){
        return ResponseEntity.ok(new StandardResponse(true, transactionService.deposit(depositWithdrawRequest),null));
    }

    @PostMapping(path = "/withdraw")
    public ResponseEntity<StandardResponse> withdraw(@RequestBody @Valid DepositWithdrawRequest depositWithdrawRequest){
        return ResponseEntity.ok(new StandardResponse(true, transactionService.withdraw(depositWithdrawRequest),null));
    }

    @PostMapping(path = "/transfer")
    public ResponseEntity<StandardResponse> transfer(@RequestBody @Valid UserTransferAmountRequest userTransferAmountRequest){
        return ResponseEntity.ok(new StandardResponse(true, transactionService.transfer(userTransferAmountRequest),null));
    }

    @GetMapping(path = "/transactions")
    public ResponseEntity<StandardResponse> userTransactions(@Valid UserIdRequest userIdRequest){
        return ResponseEntity.ok(new StandardResponse(true, transactionService.userTransactions(userIdRequest),null));

    }
}
