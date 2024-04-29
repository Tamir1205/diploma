package com.example.flatsharing.user.domain.mapper;

import com.example.flatsharing.user.domain.dto.CreateUserDTO;
import com.example.flatsharing.user.domain.dto.UpdateUserDTO;
import com.example.flatsharing.user.domain.dto.UserDTO;
import com.example.flatsharing.user.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
public interface UserMapper {
    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
    @Mapping(target = "role", expression = "java(com.example.flatsharing.user.domain.model.Role.TENANT)")
    User toUser(CreateUserDTO dto);

    @Mapping(target = "id", ignore = true)
    void mapValues(UpdateUserDTO updateUserDTO, @MappingTarget User user);

    UserDTO toDTO(User user);
}
