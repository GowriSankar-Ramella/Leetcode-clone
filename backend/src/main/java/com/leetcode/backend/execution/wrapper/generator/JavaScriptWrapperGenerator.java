package com.leetcode.backend.execution.wrapper.generator;

import com.leetcode.backend.methodsignature.entity.MethodSignature;
import com.leetcode.backend.methodsignature.repository.MethodSignatureRepository;
import com.leetcode.backend.testcases.entity.TestCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JavaScriptWrapperGenerator
        implements CodeWrapperGenerator {

    private final MethodSignatureRepository
            methodSignatureRepository;

    @Override
    public String generate(
            Long problemId,
            String userCode,
            List<TestCase> testCases
    ){

        MethodSignature methodSignature =
                methodSignatureRepository
                        .findByProblemId(problemId)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Method Signature not found"
                                )
                        );

        StringBuilder invocationBuilder =
                new StringBuilder();

        for(
                TestCase testCase
                : testCases
        ){

            String input =
                    testCase
                            .getInput()
                            .replace(
                                    " ",
                                    ","
                            );

            invocationBuilder.append(
                    """
                    console.log(
                        solution.%s(
                            %s
                        )
                    );

                    """.formatted(
                            methodSignature.getMethodName(),
                            input
                    )
            );

        }

        return userCode
                + "\n"
                + "const solution=new Solution();\n"
                + invocationBuilder;

    }

}
