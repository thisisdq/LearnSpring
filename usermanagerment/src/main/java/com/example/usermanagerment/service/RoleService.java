package com.example.usermanagerment.service;

import com.example.usermanagerment.dto.request.RoleRequest;
import com.example.usermanagerment.dto.response.RoleResponse;
import com.example.usermanagerment.mapping.RoleMapper;
import com.example.usermanagerment.repository.PermissionRepository;
import com.example.usermanagerment.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private RoleMapper roleMapper;

    public RoleResponse create(RoleRequest request){
        var role = roleMapper.toRole(request);

        var permissions = permissionRepository.findAllById(request.getPermissions());

        role.setPermissions(new HashSet<>(permissions));
        roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    public List<RoleResponse> getAll(){
        return roleRepository.findAll().stream().map(roleMapper::toRoleResponse).toList();
    }

    public void delete(String name){
        roleRepository.deleteById(name);
    }
}
