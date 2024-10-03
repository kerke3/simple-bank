package com.kerke3.simple_bank.transactions.service.impl;

import com.kerke3.simple_bank.common.dto.UserIdRequest;
import com.kerke3.simple_bank.transactions.dto.*;
import com.kerke3.simple_bank.transactions.repository.TransactionRepository;
import com.kerke3.simple_bank.transactions.service.TransactionService;
import com.kerke3.simple_bank.users.dto.SuccessResponse;
import com.kerke3.simple_bank.users.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionResponse deposit(DepositWithdrawRequest depositWithdrawRequest){
        return transactionRepository.deposit(depositWithdrawRequest.userId(),depositWithdrawRequest.accountId(),depositWithdrawRequest.amount());
    }

    public TransactionResponse withdraw(DepositWithdrawRequest depositWithdrawRequest){
        return transactionRepository.withdraw(depositWithdrawRequest.userId(),depositWithdrawRequest.accountId(),depositWithdrawRequest.amount());
    }

    public TransactionResponse transfer(UserTransferAmountRequest userTransferAmountRequest){
        return transactionRepository.transfer(userTransferAmountRequest.userId(),userTransferAmountRequest.accountId(),
                userTransferAmountRequest.recipientId(),userTransferAmountRequest.recipientAccountId(),userTransferAmountRequest.amount());
    }

    public UserTransactionsResponse userTransactions(UserIdRequest userIdRequest){
        return transactionRepository.userTransactions(userIdRequest.userId());
    }
}
