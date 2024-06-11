package com.example.flatsharing.user.service;

import com.example.flatsharing.user.domain.dto.*;
import com.example.flatsharing.user.domain.mapper.UserMapper;
import com.example.flatsharing.user.domain.model.User;
import com.example.flatsharing.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final MongoTemplate mongoTemplate;

    public RegistrationResponseDTO create(CreateUserDTO dto) {
        User user = userMapper.toUser(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        if (userRepository.existsUserByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email address already in use");
        } else {
            save(user);
            String token = jwtService.generateToken(user);
            return new RegistrationResponseDTO(userMapper.toDTO(user), token);
        }
    }

    public User getById(String id) {
        return userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("User with id =" + id + "not found"));
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new IllegalArgumentException("User with email =" + email + "not found"));
    }

    public List<User> searchUser(String parameter) {
        List<User> users = new ArrayList<>();
        if (parameter != null) {
            Query firstQuery = new Query();
            firstQuery.addCriteria(Criteria.where("firstName").regex(parameter, "i"));
            users = mongoTemplate.find(firstQuery, User.class);
        }
        if (users.isEmpty()) {
            Query secondQuery = new Query();
            secondQuery.addCriteria(Criteria.where("surname").regex(parameter, "i"));
            users = mongoTemplate.find(secondQuery, User.class);
        }
        return users;
    }

    public AuthenticationResponseDTO authenticate(AuthenticateRequestDTO dto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
        User user = getByEmail(dto.getEmail());
        String token = jwtService.generateToken(user);
        return new AuthenticationResponseDTO(userMapper.toDTO(user), token);
    }

    public User update(UpdateUserDTO dto, String id) {
        User user = getById(id);
        userMapper.mapValues(dto, user);
        return save(user);
    }

    public boolean changePassword(String id, ChangePasswordDTO dto) {
        User user = getById(id);
        if (passwordEncoder.matches(dto.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
            save(user);
            return true;
        } else {
            throw new IllegalArgumentException("Old password does not match");
        }
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
