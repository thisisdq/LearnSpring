package com.example.usermanagerment.mapping;

import com.example.usermanagerment.dto.request.RoleRequest;
import com.example.usermanagerment.dto.response.RoleResponse;
import com.example.usermanagerment.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions",ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
