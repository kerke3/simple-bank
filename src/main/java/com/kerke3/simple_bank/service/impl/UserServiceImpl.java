package com.kerke3.simple_bank.service.impl;

import com.kerke3.simple_bank.dto.*;
import com.kerke3.simple_bank.repository.UserRepository;
import com.kerke3.simple_bank.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserResponse createUser(UserIdRequest userIdRequest){
        return userRepository.createOrGet(userIdRequest.userId());
    }

    public SuccessResponse deactivateUser(UserIdRequest userIdRequest){
        return userRepository.deactivate(userIdRequest.userId());
    }

    public UserAccountsResponse openAccount(UserAccountRequest userAccountRequest){
        return userRepository.openAccount(userAccountRequest.userId(),userAccountRequest.accountId());
    }

    public UserAccountsResponse userAccounts(UserIdRequest userIdRequest){
        return userRepository.userAccounts(userIdRequest.userId());
    }

    public TransactionResponse deposit(DepositWithdrawRequest depositWithdrawRequest){
        return userRepository.deposit(depositWithdrawRequest.userId(),depositWithdrawRequest.accountId(),depositWithdrawRequest.amount());
    }

    public TransactionResponse withdraw(DepositWithdrawRequest depositWithdrawRequest){
        return userRepository.withdraw(depositWithdrawRequest.userId(),depositWithdrawRequest.accountId(),depositWithdrawRequest.amount());
    }

    public TransactionResponse transfer(UserTransferAmountRequest userTransferAmountRequest){
        return userRepository.transfer(userTransferAmountRequest.userId(),userTransferAmountRequest.accountId(),
                userTransferAmountRequest.recipientId(),userTransferAmountRequest.recipientAccountId(),userTransferAmountRequest.amount());
    }

    public UserTransactionsResponse userTransactions(UserIdRequest userIdRequest){
        return userRepository.userTransactions(userIdRequest.userId());
    }
}
