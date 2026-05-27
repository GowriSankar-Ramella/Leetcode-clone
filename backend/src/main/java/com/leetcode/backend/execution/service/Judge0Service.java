package com.leetcode.backend.execution.service;

import com.leetcode.backend.execution.dto.Judge0Request;
import com.leetcode.backend.execution.dto.Judge0Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class Judge0Service {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    private static final String JUDGE0_URL =
            "http://13.233.225.209:2358/submissions/?base64_encoded=false&wait=true";

    public Judge0Response execute(
            Judge0Request request
    ) throws Exception {

        String json =
                objectMapper.writeValueAsString(request);


        HttpHeaders headers =
                new HttpHeaders();

        headers.setContentType(
                MediaType.APPLICATION_JSON
        );

        HttpEntity<String> entity =
                new HttpEntity<>(
                        json,
                        headers
                );

        ResponseEntity<Judge0Response> response =
                restTemplate.exchange(
                        JUDGE0_URL,
                        HttpMethod.POST,
                        entity,
                        Judge0Response.class
                );

        return response.getBody();
    }
}