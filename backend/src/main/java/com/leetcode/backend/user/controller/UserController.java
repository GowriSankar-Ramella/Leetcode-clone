package com.leetcode.backend.user.controller;

import com.leetcode.backend.user.dto.*;
import com.leetcode.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService
            userService;

    @PostMapping(
            "/register"
    )
    public RegisterResponse
    register(
            @RequestBody
            RegisterRequest request
    ){

        return userService
                .register(
                        request
                );

    }

    @PostMapping(
            "/login"
    )
    public LoginResponse
    login(
            @RequestBody
            LoginRequest request
    ){

        return userService
                .login(
                        request
                );

    }

    @GetMapping("/me")
    public UserProfileResponse
    getCurrentUser(){

        return userService
                .getCurrentUserProfile();

    }

    @GetMapping("/stats")
    public UserStatsResponse
    getStats(){

        return userService
                .getUserStats();

    }

}
