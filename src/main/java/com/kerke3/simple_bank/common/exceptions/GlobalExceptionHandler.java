package com.kerke3.simple_bank.common.exceptions;

import java.util.*;
import java.util.stream.Collectors;

import com.kerke3.simple_bank.common.dto.response.StandardResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/*
    This error handling felt very important as to provide more coherent responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        return new ResponseEntity<>(new StandardResponse(false,null,errors), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<Object> handleUserNotFound(UserNotFoundException ex, WebRequest request) {
        List<String> errors = Arrays.asList(ex.getMessage());
        return new ResponseEntity<>(new StandardResponse(false,null,errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InactiveUserException.class)
    protected ResponseEntity<Object> handleInactiveUser(InactiveUserException ex, WebRequest request) {
        List<String> errors = Arrays.asList(ex.getMessage());
        return new ResponseEntity<>(new StandardResponse(false,null,errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidTransactionAmountException.class)
    protected ResponseEntity<Object> handleInvalidTransactionAmount(InvalidTransactionAmountException ex, WebRequest request) {
        List<String> errors = Arrays.asList(ex.getMessage());
        return new ResponseEntity<>(new StandardResponse(false,null,errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidOperationException.class)
    protected ResponseEntity<Object> handleInvalidOperation(InvalidOperationException ex, WebRequest request) {
        List<String> errors = Arrays.asList(ex.getMessage());
        return new ResponseEntity<>(new StandardResponse(false,null,errors), HttpStatus.BAD_REQUEST);
    }
}
