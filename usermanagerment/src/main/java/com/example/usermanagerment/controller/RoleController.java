package com.example.usermanagerment.controller;

import com.example.usermanagerment.dto.request.ApiResponse;
import com.example.usermanagerment.dto.request.RoleRequest;
import com.example.usermanagerment.dto.response.RoleResponse;
import com.example.usermanagerment.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/roles")
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    ApiResponse<RoleResponse> create(@RequestBody RoleRequest request){
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> getAll(){
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getAll())
                .build();
    }

    @PostMapping("/{roleID}")
    ApiResponse<Void> delete(@RequestBody String roleID){
        roleService.delete(roleID);
        return ApiResponse.<Void>builder().build();
    }
}
