package com.leetcode.backend.execution.controller;

import com.leetcode.backend.execution.dto.ExecuteCodeRequest;
import com.leetcode.backend.execution.dto.ExecuteCodeResponse;
import com.leetcode.backend.execution.service.ExecutionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/execute")
@RequiredArgsConstructor
public class ExecutionController {

    private final ExecutionService executionService;

    @PostMapping
    public ExecuteCodeResponse executeCode(
            @Valid @RequestBody
            ExecuteCodeRequest request
    ) {

        return executionService.executeCode(
                request
        );
    }
}
