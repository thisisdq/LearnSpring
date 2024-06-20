package com.example.usermanagerment.mapping;

import com.example.usermanagerment.dto.request.UserCreationRequest;
import com.example.usermanagerment.dto.request.UserUpdateRequest;
import com.example.usermanagerment.dto.response.UserResponse;
import com.example.usermanagerment.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    void update(@MappingTarget User user, UserUpdateRequest request);
    @Mapping(target = "roles",ignore = true)
    UserResponse toUserResponse(User user);
}
