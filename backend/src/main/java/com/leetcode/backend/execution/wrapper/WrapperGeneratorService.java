package com.leetcode.backend.execution.wrapper;

import com.leetcode.backend.execution.wrapper.generator.JavaScriptWrapperGenerator;
import com.leetcode.backend.execution.wrapper.generator.JavaWrapperGenerator;
import com.leetcode.backend.execution.wrapper.generator.PythonWrapperGenerator;
import com.leetcode.backend.problem.enums.Language;
import com.leetcode.backend.testcases.entity.TestCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WrapperGeneratorService {

    private final JavaWrapperGenerator
            javaWrapperGenerator;

    private final PythonWrapperGenerator
            pythonWrapperGenerator;

    private  final JavaScriptWrapperGenerator
            javaScriptWrapperGenerator;

    public String generate(
            Long problemId,
            Language language,
            String userCode,
            List<TestCase> testCases
    ){

        switch(language){

            case JAVA:
                return javaWrapperGenerator
                        .generate(
                                problemId,
                                userCode,
                                testCases
                        );
            case PYTHON:
                return pythonWrapperGenerator
                        .generate(
                                problemId,
                                userCode,
                                testCases
                        );

            case JAVASCRIPT:
                return javaScriptWrapperGenerator
                        .generate(
                                problemId,
                                userCode,
                                testCases
                        );

            default:
                throw new RuntimeException(
                        "Unsupported language"
                );

        }

    }

}
