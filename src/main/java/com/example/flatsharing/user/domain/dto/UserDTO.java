package com.example.flatsharing.user.domain.dto;

import com.example.flatsharing.user.domain.model.Interest;
import com.example.flatsharing.user.domain.model.Role;
import com.example.flatsharing.user.domain.model.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String id;
    private String email;
    private Sex sex;
    private String city;
    private List<Interest> interests;
    private String firstname;
    private String lastname;
    private Role role;
    private List<String> publicationIds;
}
