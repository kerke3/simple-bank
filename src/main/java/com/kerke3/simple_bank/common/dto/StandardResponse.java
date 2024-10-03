package com.kerke3.simple_bank.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record StandardResponse<T,V>(Boolean success, T payload, V errors) {
}
