package com.kerke3.simple_bank.accounts.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserAccountRequest(@NotNull@NotEmpty(message = "userId field is required") String userId, @NotNull@NotEmpty(message = "accountId field is required") String accountId) {
}
