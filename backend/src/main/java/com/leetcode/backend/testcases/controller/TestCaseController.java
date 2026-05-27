package com.leetcode.backend.testcases.controller;

import com.leetcode.backend.testcases.dto.CreateTestCaseRequest;
import com.leetcode.backend.testcases.dto.TestCaseResponse;
import com.leetcode.backend.testcases.dto.UpdateTestCaseRequest;
import com.leetcode.backend.testcases.service.TestCaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestCaseController {

    private final TestCaseService testCaseService;

    @PostMapping("/problems/{problemId}/test-cases")
    @ResponseStatus(HttpStatus.CREATED)
    public TestCaseResponse createTestCase(
            @PathVariable Long problemId,
            @Valid @RequestBody
            CreateTestCaseRequest request
    ) {

        return testCaseService.createTestCase(
                problemId,
                request
        );
    }

    @GetMapping("/problems/{problemId}/test-cases")
    public List<TestCaseResponse> getTestCases(
            @PathVariable Long problemId
    ) {

        return testCaseService.getTestCasesByProblem(
                problemId
        );
    }

    @GetMapping("/problems/{problemId}/public-testcases")
    public List<TestCaseResponse> getPublicTestCases(
            @PathVariable
            Long problemId
    ){

        return testCaseService
                .getPublicTestCases(
                        problemId
                );

    }

    @PutMapping("/test-cases/{id}")
    public TestCaseResponse updateTestCase(
            @PathVariable Long id,
            @Valid @RequestBody
            UpdateTestCaseRequest request
    ) {

        return testCaseService.updateTestCase(
                id,
                request
        );
    }

    @DeleteMapping("/test-cases/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTestCase(
            @PathVariable Long id
    ) {

        testCaseService.deleteTestCase(id);
    }
}
