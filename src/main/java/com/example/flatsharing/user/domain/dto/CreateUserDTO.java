package com.example.flatsharing.user.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDTO {
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String surname;
    private String phoneNumber;
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String sex;
    @NotEmpty
    private Integer age;
    @NotEmpty
    private List<String> interests;
    private String profilePicture;
    private String description;
}
