package com.example.flatsharing.user.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDTO {
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String sex;
    @NotEmpty
    private String city;
//    @NotEmpty
//    private List<String> interests;
    @NotEmpty
    private String firstname;
//    @NotEmpty
//    private String lastname;
    private String phoneNumber;
}
