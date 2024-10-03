package com.kerke3.simple_bank.transactions.service;

import com.kerke3.simple_bank.common.dto.UserIdRequest;
import com.kerke3.simple_bank.transactions.dto.*;

public interface TransactionService {
    TransactionResponse deposit(DepositWithdrawRequest depositWithdrawRequest);
    TransactionResponse withdraw(DepositWithdrawRequest depositWithdrawRequest);
    UserTransactionsResponse userTransactions(UserIdRequest userIdRequest);
    TransactionResponse transfer(UserTransferAmountRequest userTransferAmountRequest);
}
