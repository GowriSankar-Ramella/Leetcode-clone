package com.leetcode.backend.testcases.service;

import com.leetcode.backend.common.exception.ResourceNotFoundException;
import com.leetcode.backend.problem.entity.Problem;
import com.leetcode.backend.problem.repository.ProblemRepository;
import com.leetcode.backend.testcases.dto.CreateTestCaseRequest;
import com.leetcode.backend.testcases.dto.TestCaseResponse;
import com.leetcode.backend.testcases.dto.UpdateTestCaseRequest;
import com.leetcode.backend.testcases.entity.TestCase;
import com.leetcode.backend.testcases.repository.TestCaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestCaseService {

    private final TestCaseRepository testCaseRepository;
    private final ProblemRepository problemRepository;

    public TestCaseResponse createTestCase(
            Long problemId,
            CreateTestCaseRequest request
    ) {

        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Problem not found with id: " + problemId
                        )
                );

        TestCase testCase = TestCase.builder()
                .input(request.getInput())
                .expectedOutput(request.getExpectedOutput())
                .hidden(request.isHidden())
                .problem(problem)
                .build();

        TestCase savedTestCase =
                testCaseRepository.save(testCase);

        return mapToResponse(savedTestCase);
    }

    public List<TestCaseResponse> getTestCasesByProblem(
            Long problemId
    ) {

        return testCaseRepository.findByProblemId(problemId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<TestCaseResponse> getPublicTestCases(
            Long problemId
    ){

        return testCaseRepository.findByProblemIdAndHiddenFalse(problemId)
                .stream()
                .map(this::mapToResponse)
                .toList();

    }

    public TestCaseResponse updateTestCase(
            Long id,
            UpdateTestCaseRequest request
    ) {

        TestCase testCase = testCaseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Test case not found with id: " + id
                        )
                );

        testCase.setInput(request.getInput());
        testCase.setExpectedOutput(
                request.getExpectedOutput()
        );
        testCase.setHidden(request.isHidden());

        TestCase updatedTestCase =
                testCaseRepository.save(testCase);

        return mapToResponse(updatedTestCase);
    }

    public void deleteTestCase(Long id) {

        TestCase testCase = testCaseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Test case not found with id: " + id
                        )
                );

        testCaseRepository.delete(testCase);
    }

    private TestCaseResponse mapToResponse(
            TestCase testCase
    ) {

        return TestCaseResponse.builder()
                .id(testCase.getId())
                .input(testCase.getInput())
                .expectedOutput(
                        testCase.getExpectedOutput()
                )
                .hidden(testCase.isHidden())
                .build();
    }
}
