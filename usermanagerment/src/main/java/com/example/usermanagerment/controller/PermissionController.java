package com.example.usermanagerment.controller;

import com.example.usermanagerment.dto.request.ApiResponse;
import com.example.usermanagerment.dto.request.PermissionRequest;
import com.example.usermanagerment.dto.response.PermissionResponse;
import com.example.usermanagerment.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/permissions")
@RestController
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping
    ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest request){
    return ApiResponse.<PermissionResponse>builder()
            .result(permissionService.create(request))
            .build();
    }

    @GetMapping
    ApiResponse<List<PermissionResponse>> getAll(){
        return ApiResponse.<List<PermissionResponse>>builder()
                .result(permissionService.getAll())
                .build();
    }

    @PostMapping("/{permissionID}")
    ApiResponse<Void> delete(@RequestBody String permissionID){
        permissionService.delete(permissionID);
        return ApiResponse.<Void>builder().build();
    }
}
