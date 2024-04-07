package com.example.flatsharing.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDTO {
    private String email;
    private String password;
    private String sex;
    private String city;
    //    private List<Interest> interests;
    private String firstname;
    //    private String lastname;
//    private String role;
    private String phoneNumber;
}
