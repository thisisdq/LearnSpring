package com.example.usermanagerment.service;

import com.example.usermanagerment.dto.request.ApiResponse;
import com.example.usermanagerment.dto.request.UserCreationRequest;
import com.example.usermanagerment.dto.request.UserUpdateRequest;
import com.example.usermanagerment.dto.response.UserResponse;
import com.example.usermanagerment.entity.User;
//import com.example.usermanagerment.enums.Role;
import com.example.usermanagerment.exceptionHandling.AppException;
import com.example.usermanagerment.exceptionHandling.ErrorCode;
import com.example.usermanagerment.mapping.UserMapper;
import com.example.usermanagerment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse createRequest(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXIST);
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        HashSet<String> roles = new HashSet<>();
//        roles.add(Role.USER.name());
//        user.setRoles(roles);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse createAdmin(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXIST);
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        HashSet<String> roles = new HashSet<>();
//        roles.add(Role.ADMIN.name());
//        user.setRoles(roles);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<List<UserResponse>> getUsers() {
        List<UserResponse> userResponses = new ArrayList<>();
        List<User> u = userRepository.findAll();
        u.forEach(us -> {
            userResponses.add(userMapper.toUserResponse(us));
        });
        return ApiResponse.<List<UserResponse>>builder()
                .result(userResponses)
                .build();
    }

    @PostAuthorize("returnObject.username == authentication.username")
    public UserResponse getUser(String userID) {
        return userMapper.toUserResponse(
                userRepository
                        .findById(userID)
                        .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND))
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse updateUser(String userID, UserUpdateRequest request) {
        User user = userRepository.findById(userID).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_FOUND)
        );
        userMapper.update(user, request);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(String userID) {
        userRepository.deleteById(userID);
    }
}
