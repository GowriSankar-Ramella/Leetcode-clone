package com.leetcode.backend.execution.service;

import com.leetcode.backend.execution.dto.*;
import com.leetcode.backend.execution.enums.ExecutionMode;
import com.leetcode.backend.execution.mapper.LanguageIdMapper;
import com.leetcode.backend.execution.wrapper.WrapperGeneratorService;
import com.leetcode.backend.security.service.AuthService;
import com.leetcode.backend.submission.Submission;
import com.leetcode.backend.submission.SubmissionRepository;
import com.leetcode.backend.testcases.entity.TestCase;
import com.leetcode.backend.testcases.repository.TestCaseRepository;
import com.leetcode.backend.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExecutionService {

    private final Judge0Service judge0Service;
    private final WrapperGeneratorService wrapperGeneratorService;
    private final TestCaseRepository testCaseRepository;
    private final SubmissionRepository submissionRepository;
    private final AuthService authService;

    public ExecuteCodeResponse executeCode(
            ExecuteCodeRequest request
    ) {

        List<TestCase> testCases =
                getTestCases(
                        request
                );

        Judge0Request judgeRequest =
                new Judge0Request();

        String generatedCode =
                wrapperGeneratorService.generate(
                        request.getProblemId(),
                        request.getLanguage(),
                        request.getCode(),
                        testCases
                );

        judgeRequest.setSourceCode(
                generatedCode
        );

        judgeRequest.setLanguageId(
                LanguageIdMapper.getLanguageId(
                        request.getLanguage()
                )
        );

        Judge0Response response =
                null;
        try {
            response = judge0Service.execute(
                    judgeRequest
            );
            ExecuteCodeResponse errorResponse =
                    handleJudge0Errors(
                            response
                    );

            if(errorResponse!=null){

                saveSubmission(
                        request,
                        errorResponse
                );

                return errorResponse;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        TestCaseResult result =
                evaluate(
                        response.getStdout(),
                        testCases
                );

        ExecuteCodeResponse executeCodeResponse =
                ExecuteCodeResponse
                        .builder()
                        .passed(
                                result.getPassed()
                        )
                        .total(
                                result.getTotal()
                        )
                        .status(
                                result.getStatus()
                        )
                        .failedInput(
                                result.getFailedInput()
                        )
                        .expected(
                                result.getExpected()
                        )
                        .actual(
                                result.getActual()
                        )
                        .build();

        saveSubmission(
                request,
                executeCodeResponse
        );

        return executeCodeResponse;
    }

    private List<TestCase> getTestCases(
            ExecuteCodeRequest request
    ){

        if(
                request.getMode()
                        ==
                        ExecutionMode.RUN
        ){

            return testCaseRepository
                    .findByProblemIdAndHidden(
                            request.getProblemId(),
                            false
                    );

        }

        return testCaseRepository
                .findByProblemId(
                        request.getProblemId()
                );

    }

    private TestCaseResult evaluate(
            String output,
            List<TestCase> testCases
    ){

        String[] actualOutputs =
                output
                        .trim()
                        .split("\n");

        int passed = 0;

        for(
                int i=0;
                i<testCases.size();
                i++
        ){

            String expected =
                    testCases
                            .get(i)
                            .getExpectedOutput()
                            .trim();

            String actual =
                    actualOutputs[i]
                            .trim();

            if(
                    expected.equals(
                            actual
                    )
            ){
                passed++;
            }
            else{

                return TestCaseResult
                        .builder()
                        .passed(
                                passed
                        )
                        .total(
                                testCases.size()
                        )
                        .status(
                                "Wrong Answer"
                        )
                        .failedInput(
                                testCases
                                        .get(i)
                                        .getInput()
                        )
                        .expected(
                                expected
                        )
                        .actual(
                                actual
                        )
                        .build();

            }

        }

        return TestCaseResult
                .builder()
                .passed(
                        passed
                )
                .total(
                        testCases.size()
                )
                .status(
                        "Accepted"
                )
                .build();

    }

    private ExecuteCodeResponse handleJudge0Errors(
            Judge0Response response
    ){

        String status =
                response.getStatus()
                        .getDescription();

        if(
                !"Accepted".equals(
                        status
                )
        ){

            String errorMessage = null;

            if(
                    response.getCompile_output()
                            != null
            ){

                errorMessage =
                        response.getCompile_output();

            }
            else if(
                    response.getStderr()
                            != null
            ){

                errorMessage =
                        response.getStderr();

            }
            else{

                errorMessage =
                        response.getMessage();

            }

            return ExecuteCodeResponse
                    .builder()
                    .status(
                            status
                    )
                    .error(
                            errorMessage
                    )
                    .build();

        }

        return null;

    }

    private void saveSubmission(
            ExecuteCodeRequest request,
            ExecuteCodeResponse response
    ){

        User currentUser =
                authService
                        .getCurrentUser();

        Submission submission =
                Submission.builder()
                        .user(
                                currentUser
                        )
                        .problemId(
                                request.getProblemId()
                        )
                        .language(
                                request.getLanguage()
                        )
                        .mode(
                                request.getMode()
                        )
                        .code(
                                request.getCode()
                        )
                        .passed(
                                response.getPassed()
                        )
                        .total(
                                response.getTotal()
                        )
                        .status(
                                response.getStatus()
                        )
                        .error(
                                response.getError()
                        )
                        .createdAt(
                                LocalDateTime.now()
                        )
                        .build();

        submissionRepository.save(
                submission
        );

    }
}
