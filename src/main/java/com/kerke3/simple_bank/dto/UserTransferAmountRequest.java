package com.kerke3.simple_bank.dto;

import jakarta.validation.constraints.NotNull;

public record UserTransferAmountRequest(@NotNull(message = "userId field is required") String userId, @NotNull(message = "accountId field is required") String accountId,
    @NotNull(message = "recipientId field is required") String recipientId, @NotNull(message = "recipientAccountId field is required") String recipientAccountId,  double amount) {
}
