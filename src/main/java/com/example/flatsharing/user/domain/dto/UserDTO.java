package com.example.flatsharing.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String id;
    private String email;
    private String sex;
    private String firstName;
    private String surname;
    private String phoneNumber;
    private Integer age;
    private List<String> interests;
    private String description;
    private String profilePicture;
    private List<String> advertisementIds;
    private List<String> likedAdvertisementIds;
}
