package com.example.flatsharing.user.controller;

import com.example.flatsharing.user.domain.dto.*;
import com.example.flatsharing.user.domain.mapper.UserMapper;
import com.example.flatsharing.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<RegistrationResponseDTO> createUser(@RequestBody CreateUserDTO dto) {
        return ResponseEntity.ok(userService.create(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody AuthenticateRequestDTO dto) {
        return ResponseEntity.ok(userService.authenticate(dto));
    }

    @PutMapping("/update/users/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UpdateUserDTO dto, @PathVariable String id) {
        return ResponseEntity.ok(userMapper.toDTO(userService.update(dto, id)));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(userMapper.toDTO(userService.getById(id)));
    }

    @GetMapping("/users/email/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userMapper.toDTO(userService.getByEmail(email)));
    }

    @PostMapping("/users/{id}/password")
    public ResponseEntity<Boolean> changeUserPassword(@PathVariable String id, @RequestBody ChangePasswordDTO dto) {
        return ResponseEntity.ok(userService.changePassword(id, dto));
    }
}
