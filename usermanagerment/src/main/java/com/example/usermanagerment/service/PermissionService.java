package com.example.usermanagerment.service;

import com.example.usermanagerment.dto.request.PermissionRequest;
import com.example.usermanagerment.dto.response.PermissionResponse;
import com.example.usermanagerment.entity.Permission;
import com.example.usermanagerment.mapping.PermissionMapper;
import com.example.usermanagerment.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private PermissionRepository permissionRepository;
    public PermissionResponse create(PermissionRequest request){
        Permission permission = permissionMapper.toPermission(request);
        permission = permissionRepository.save(permission);
        return permissionMapper.toUserResponse(permission);
    }

    public List<PermissionResponse> getAll(){
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toUserResponse).toList();
    }

    public void delete(String permission){
        permissionRepository.deleteById(permission);
    }
}
