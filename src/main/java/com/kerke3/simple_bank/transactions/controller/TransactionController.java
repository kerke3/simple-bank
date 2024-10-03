package com.kerke3.simple_bank.transactions.controller;


import com.kerke3.simple_bank.common.dto.request.UserIdRequest;
import com.kerke3.simple_bank.transactions.dto.request.DepositWithdrawRequest;
import com.kerke3.simple_bank.transactions.dto.response.TransactionResponse;
import com.kerke3.simple_bank.transactions.dto.response.UserTransactionsResponse;
import com.kerke3.simple_bank.transactions.dto.request.UserTransferAmountRequest;
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
    public ResponseEntity<TransactionResponse> deposit(@RequestBody @Valid DepositWithdrawRequest depositWithdrawRequest){
        return ResponseEntity.ok(transactionService.deposit(depositWithdrawRequest));
    }

    @PostMapping(path = "/withdraw")
    public ResponseEntity<TransactionResponse> withdraw(@RequestBody @Valid DepositWithdrawRequest depositWithdrawRequest){
        return ResponseEntity.ok(transactionService.withdraw(depositWithdrawRequest));
    }

    @PostMapping(path = "/transfer")
    public ResponseEntity<TransactionResponse> transfer(@RequestBody @Valid UserTransferAmountRequest userTransferAmountRequest){
        return ResponseEntity.ok(transactionService.transfer(userTransferAmountRequest));
    }

    @GetMapping(path = "/transactions")
    public ResponseEntity<UserTransactionsResponse> userTransactions(@Valid UserIdRequest userIdRequest){
        return ResponseEntity.ok(transactionService.userTransactions(userIdRequest));

    }
}
