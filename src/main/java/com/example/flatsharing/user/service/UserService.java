package com.example.flatsharing.user.service;

import com.example.flatsharing.user.domain.dto.AuthenticateRequestDTO;
import com.example.flatsharing.user.domain.dto.CreateUserDTO;
import com.example.flatsharing.user.domain.dto.UpdateUserDTO;
import com.example.flatsharing.user.domain.dto.UserDTO;
import com.example.flatsharing.user.domain.mapper.UserMapper;
import com.example.flatsharing.user.domain.model.User;
import com.example.flatsharing.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public String create(CreateUserDTO dto) {
        User user = userMapper.toUser(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(user);
        return jwtService.generateToken(user);
    }

    public String authenticate(AuthenticateRequestDTO dto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow();
        return jwtService.generateToken(user);
    }

    public User update(UpdateUserDTO dto, String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Not found user with id" + id));
        userMapper.mapValues(dto, user);
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        userRepository.save(user);
        return user;
    }
}
