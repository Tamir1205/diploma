package com.example.flatsharing.user.controller;

import com.example.flatsharing.user.domain.dto.AuthenticateRequestDTO;
import com.example.flatsharing.user.domain.dto.CreateUserDTO;
import com.example.flatsharing.user.domain.dto.UpdateUserDTO;
import com.example.flatsharing.user.domain.dto.UserDTO;
import com.example.flatsharing.user.domain.mapper.UserMapper;
import com.example.flatsharing.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String createUser(@RequestBody CreateUserDTO dto) {
        return userService.create(dto);
    }

    @PostMapping("/login")
    public String createUser(@RequestBody AuthenticateRequestDTO dto) {
        return userService.authenticate(dto);
    }

    @PutMapping("/users/{id}")
    public UserDTO updateUser(@RequestBody UpdateUserDTO dto, @PathVariable String id) {
        return userMapper.toDTO(userService.update(dto, id));
    }
}
