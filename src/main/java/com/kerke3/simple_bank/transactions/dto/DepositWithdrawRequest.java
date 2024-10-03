package com.kerke3.simple_bank.transactions.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record DepositWithdrawRequest(@NotNull@NotEmpty(message = "userId field is required") String userId, @NotNull@NotEmpty(message = "accountId field is required") String accountId, @NotNull @Min(value = 0, message = "amount must be greater than 0") double amount) {
}
