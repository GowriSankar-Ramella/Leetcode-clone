package com.leetcode.backend.user.service;

import com.leetcode.backend.security.enums.Role;
import com.leetcode.backend.security.service.AuthService;
import com.leetcode.backend.security.service.JwtService;
import com.leetcode.backend.submission.SubmissionRepository;
import com.leetcode.backend.user.dto.*;
import com.leetcode.backend.user.entity.User;
import com.leetcode.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository
            userRepository;

    private final PasswordEncoder
            passwordEncoder;

    private final JwtService
            jwtService;

    private final AuthService
            authService;

    private final SubmissionRepository
            submissionRepository;

    public RegisterResponse register(
            RegisterRequest request
    ){

        if(
                userRepository.existsByEmail(
                        request.getEmail()
                )
        ){

            throw new RuntimeException(
                    "Email already exists"
            );

        }

        User user =
                User.builder()
                        .name(
                                request.getName()
                        )
                        .email(
                                request.getEmail()
                        )
                        .password(
                                passwordEncoder.encode(
                                        request.getPassword()
                                )
                        )
                        .role(
                                Role.USER
                        )
                        .build();

        user =
                userRepository.save(
                        user
                );

        return RegisterResponse
                .builder()
                .id(
                        user.getId()
                )
                .name(
                        user.getName()
                )
                .email(
                        user.getEmail()
                )
                .role(
                        user.getRole().name()
                )
                .build();

    }

    public LoginResponse login(
            LoginRequest request
    ){

        User user =
                userRepository
                        .findByEmail(
                                request.getEmail()
                        )
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "User not found"
                                )
                        );

        if(
                !passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                )
        ){

            throw new RuntimeException(
                    "Invalid credentials"
            );

        }

        String token =
                jwtService.generateToken(
                        user.getEmail()
                );

        return LoginResponse
                .builder()
                .token(
                        token
                )
                .email(
                        user.getEmail()
                )
                .build();

    }

    public UserProfileResponse
    getCurrentUserProfile(){

        User currentUser =
                authService
                        .getCurrentUser();

        return UserProfileResponse
                .builder()
                .id(
                        currentUser.getId()
                )
                .name(
                        currentUser.getName()
                )
                .email(
                        currentUser.getEmail()
                )
                .role(
                        currentUser
                                .getRole()
                                .name()
                )
                .build();

    }

    public UserStatsResponse getUserStats(){

        User currentUser =
                authService
                        .getCurrentUser();

        Long totalSubmissions =
                submissionRepository
                        .countByUserId(
                                currentUser.getId()
                        );

        Long acceptedSubmissions =
                submissionRepository
                        .countByUserIdAndStatus(
                                currentUser.getId(),
                                "Accepted"
                        );

        double acceptanceRate = 0;

        if(totalSubmissions>0){

            acceptanceRate =
                    (
                            acceptedSubmissions
                                    *100.0
                    )
                            /
                            totalSubmissions;

        }

        return UserStatsResponse
                .builder()
                .totalSubmissions(
                        totalSubmissions
                )
                .acceptedSubmissions(
                        acceptedSubmissions
                )
                .acceptanceRate(
                        acceptanceRate
                )
                .build();

    }

}