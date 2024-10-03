package com.kerke3.simple_bank.users.controller;

import com.kerke3.simple_bank.common.dto.request.UserIdRequest;
import com.kerke3.simple_bank.users.dto.response.SuccessResponse;
import com.kerke3.simple_bank.users.dto.request.UserResponse;
import com.kerke3.simple_bank.users.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(path = "/create")
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserIdRequest userIdRequest){
        return ResponseEntity.ok(userService.createUser(userIdRequest));
    }

    @PostMapping(path = "/deactivate")
    public ResponseEntity<SuccessResponse> deactivateUser(@RequestBody @Valid UserIdRequest userIdRequest) {
            return ResponseEntity.ok(userService.deactivateUser(userIdRequest));
    }

}
