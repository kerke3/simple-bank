package com.kerke3.simple_bank.users.repository;


import com.kerke3.simple_bank.accounts.dto.UserAccountsResponse;
import com.kerke3.simple_bank.transactions.dto.TransactionResponse;
import com.kerke3.simple_bank.transactions.dto.UserTransactionsResponse;
import com.kerke3.simple_bank.users.dto.*;

public interface UserRepository {
    UserResponse createOrGet(String userId);

    SuccessResponse deactivate(String userId);

}
