package com.kerke3.simple_bank.controller;

import com.kerke3.simple_bank.dto.*;
import com.kerke3.simple_bank.exceptions.InactiveUserException;
import com.kerke3.simple_bank.exceptions.UserNotFoundException;
import com.kerke3.simple_bank.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl accountServiceImpl;

    @PostMapping(path = "/create")
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserIdRequest userIdRequest){
        return ResponseEntity.ok(accountServiceImpl.createUser(userIdRequest));
    }

    @PostMapping(path = "/deactivate")
    public ResponseEntity<SuccessResponse> deactivateUser(@RequestBody @Valid UserIdRequest userIdRequest) throws UserNotFoundException, InactiveUserException {
            return ResponseEntity.ok(accountServiceImpl.deactivateUser(userIdRequest));
    }

    @GetMapping(path = "/open-account")
    public ResponseEntity<UserAccountsResponse> openAccount(@RequestBody @Valid UserAccountRequest userAccountRequest){
        return ResponseEntity.ok(accountServiceImpl.openAccount(userAccountRequest));

    }

    @GetMapping(path = "/accounts")
    public ResponseEntity<UserAccountsResponse> userAccounts(@RequestBody @Valid UserIdRequest userIdRequest){
        return ResponseEntity.ok(accountServiceImpl.userAccounts(userIdRequest));

    }
}
