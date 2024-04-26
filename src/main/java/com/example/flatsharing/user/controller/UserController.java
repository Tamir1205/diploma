package com.example.flatsharing.user.controller;

import com.example.flatsharing.user.domain.dto.AuthenticateRequestDTO;
import com.example.flatsharing.user.domain.dto.CreateUserDTO;
import com.example.flatsharing.user.domain.dto.UpdateUserDTO;
import com.example.flatsharing.user.domain.dto.UserDTO;
import com.example.flatsharing.user.domain.mapper.UserMapper;
import com.example.flatsharing.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody CreateUserDTO dto) {
        try {
            return ResponseEntity.ok(userService.create(dto));
        } catch (Exception e) {
            ErrorDetails errorDetails = new ErrorDetails("Error occurred while creating user");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> createUser(@RequestBody AuthenticateRequestDTO dto) {
        try {
            return ResponseEntity.ok(userService.authenticate(dto));
        } catch (Exception e) {
            ErrorDetails errorDetails = new ErrorDetails("Error occurred while login user");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<? extends Object> updateUser(@RequestBody UpdateUserDTO dto, @PathVariable String id) {
        try {
            return ResponseEntity.ok(userService.update(dto, id));
        } catch (Exception e) {
            ErrorDetails errorDetails = new ErrorDetails("Error occurred while updating user");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }

    public class ErrorDetails {
        private String message;

        public ErrorDetails(String message) {
            this.message = message;
        }
    }
}
