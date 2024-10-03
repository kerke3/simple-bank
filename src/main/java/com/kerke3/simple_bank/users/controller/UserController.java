package com.kerke3.simple_bank.users.controller;


import com.kerke3.simple_bank.common.dto.StandardResponse;
import com.kerke3.simple_bank.common.dto.UserIdRequest;
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
    public ResponseEntity<StandardResponse> createUser(@RequestBody @Valid UserIdRequest userIdRequest){
        return ResponseEntity.ok(new StandardResponse(true, userService.createUser(userIdRequest),null));
    }

    @PostMapping(path = "/deactivate")
    public ResponseEntity<StandardResponse> deactivateUser(@RequestBody @Valid UserIdRequest userIdRequest) {
            return ResponseEntity.ok(new StandardResponse(true, userService.deactivateUser(userIdRequest),null));
    }

}
