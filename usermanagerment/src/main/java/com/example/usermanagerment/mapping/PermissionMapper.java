package com.example.usermanagerment.mapping;

import com.example.usermanagerment.dto.request.PermissionRequest;
import com.example.usermanagerment.dto.response.PermissionResponse;
import com.example.usermanagerment.entity.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    void update(@MappingTarget Permission permission, PermissionRequest request);
    PermissionResponse toUserResponse(Permission permission);
}
