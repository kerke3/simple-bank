package com.kerke3.simple_bank.service;

import com.kerke3.simple_bank.dto.*;

public interface UserService {
    UserResponse createUser(UserIdRequest userIdRequest);
    SuccessResponse deactivateUser(UserIdRequest userIdRequest);

    UserAccountsResponse userAccounts(UserIdRequest userIdRequest);

    UserAccountsResponse openAccount(UserAccountRequest userAccountRequest);

    TransactionResponse deposit(DepositWithdrawRequest depositWithdrawRequest);
    TransactionResponse withdraw(DepositWithdrawRequest depositWithdrawRequest);
    UserTransactionsResponse userTransactions(UserIdRequest userIdRequest);
    TransactionResponse transfer(UserTransferAmountRequest userTransferAmountRequest);
}
