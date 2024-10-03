package com.kerke3.simple_bank.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserIdRequest(@NotNull@NotEmpty(message = "userId field is required") String userId) {
}
