package com.kerke3.simple_bank.dto;

import jakarta.validation.constraints.NotNull;

public record UserAccountRequest(@NotNull(message = "userId field is required") String userId, @NotNull(message = "accountId field is required") String accountId) {
}
