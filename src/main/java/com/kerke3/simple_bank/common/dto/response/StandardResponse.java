package com.kerke3.simple_bank.common.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record StandardResponse<T,V>(Boolean success, T payload, V errors) {
}
