package com.kerke3.simple_bank.transactions.service.impl;

import com.kerke3.simple_bank.common.dto.request.UserIdRequest;
import com.kerke3.simple_bank.transactions.dto.request.DepositWithdrawRequest;
import com.kerke3.simple_bank.transactions.dto.request.UserTransferAmountRequest;
import com.kerke3.simple_bank.transactions.dto.response.TransactionResponse;
import com.kerke3.simple_bank.transactions.dto.response.UserTransactionsResponse;
import com.kerke3.simple_bank.transactions.repository.TransactionRepository;
import com.kerke3.simple_bank.transactions.service.TransactionService;
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
