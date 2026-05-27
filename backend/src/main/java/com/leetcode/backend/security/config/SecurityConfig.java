package com.leetcode.backend.security.config;

import com.leetcode.backend.security.filter.JwtAuthenticationFilter;
import com.leetcode.backend.security.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter
            jwtAuthenticationFilter;

    private final CustomUserDetailsService
            customUserDetailsService;

    @Bean
    public SecurityFilterChain
    securityFilterChain(
            HttpSecurity http
    )
            throws Exception{

        return http

                .csrf(
                        csrf ->
                                csrf.disable()
                )

                .cors(
                        cors -> {}
                )

                .authorizeHttpRequests(
                        auth ->
                                auth
                                        .requestMatchers(
                                                "/auth/**"
                                        )
                                        .permitAll()

                                        .anyRequest()
                                        .authenticated()
                )

                .userDetailsService(
                        customUserDetailsService
                )

                .sessionManagement(
                        session ->
                                session.sessionCreationPolicy(
                                        SessionCreationPolicy.STATELESS
                                )
                )

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                )

                .build();

    }

}
