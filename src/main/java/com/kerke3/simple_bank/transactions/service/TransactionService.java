package com.kerke3.simple_bank.transactions.service;

import com.kerke3.simple_bank.common.dto.request.UserIdRequest;
import com.kerke3.simple_bank.transactions.dto.request.DepositWithdrawRequest;
import com.kerke3.simple_bank.transactions.dto.request.UserTransferAmountRequest;
import com.kerke3.simple_bank.transactions.dto.response.TransactionResponse;
import com.kerke3.simple_bank.transactions.dto.response.UserTransactionsResponse;

public interface TransactionService {
    TransactionResponse deposit(DepositWithdrawRequest depositWithdrawRequest);
    TransactionResponse withdraw(DepositWithdrawRequest depositWithdrawRequest);
    UserTransactionsResponse userTransactions(UserIdRequest userIdRequest);
    TransactionResponse transfer(UserTransferAmountRequest userTransferAmountRequest);
}
