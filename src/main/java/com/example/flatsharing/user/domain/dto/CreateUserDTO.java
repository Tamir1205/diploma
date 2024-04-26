package com.example.flatsharing.user.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDTO {
    @NotEmpty
    private String fullName;
    private String phoneNumber;
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String sex;
    @NotEmpty
    private String city;
}
