package com.leetcode.backend.execution.wrapper.generator;

import com.leetcode.backend.execution.dto.ParameterDto;
import com.leetcode.backend.methodsignature.entity.MethodSignature;
import com.leetcode.backend.methodsignature.repository.MethodSignatureRepository;
import com.leetcode.backend.testcases.entity.TestCase;
import com.leetcode.backend.testcases.repository.TestCaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JavaWrapperGenerator
        implements CodeWrapperGenerator {

    private final MethodSignatureRepository
            methodSignatureRepository;

    private final TestCaseRepository
            testCaseRepository;

    private final ObjectMapper objectMapper;

    public String generate(
            Long problemId,
            String userCode,
            List<TestCase> testCases
    ) {

        MethodSignature methodSignature =
                methodSignatureRepository
                        .findByProblemId(problemId)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Method signature not found"
                                )
                        );


        if(testCases.isEmpty()){
            throw new RuntimeException(
                    "No visible test cases found"
            );
        }


        StringBuilder invocationBuilder =
                new StringBuilder();

        for(
                TestCase testCase
                : testCases
        ){

            String invocation =
                    "solution."
                            + methodSignature.getMethodName()
                            + "("
                            + buildArguments(
                            methodSignature,
                            testCase.getInput()
                    )
                            + ")";

            invocationBuilder.append(
                    """
                    System.out.println(
                            %s
                    );
                    """.formatted(
                            invocation
                    )
            );

        }

        String wrapper =
                """
                public class Main {
        
                    public static void main(
                            String[] args
                    ){
        
                        Solution solution =
                                new Solution();
        
                        %s
        
                    }
        
                }
                """.formatted(
                        invocationBuilder
                );

        return wrapper + "\n" + userCode;
    }

    private String buildArguments(
            MethodSignature methodSignature,
            String input
    ){

        try{

            List<ParameterDto> parameters =
                    objectMapper.readValue(
                            methodSignature.getParameters(),
                            new TypeReference<List<ParameterDto>>(){}
                    );

            String[] inputValues;

            if(input.startsWith("[")){

                inputValues = new String[]{input};

            }
            else{

                inputValues = input.split(" ");

            }

            StringBuilder arguments =
                    new StringBuilder();

            for(
                    int i=0;
                    i<inputValues.length;
                    i++
            ){

                String type =
                        parameters
                                .get(i)
                                .getType();

                arguments.append(
                        convertValue(
                                type,
                                inputValues[i]
                        )
                );

                if(
                        i<inputValues.length-1
                ){
                    arguments.append(",");
                }

            }

            return arguments.toString();

        }
        catch(Exception e){

            throw new RuntimeException(e);

        }

    }

    private String convertValue(
            String type,
            String value
    ){

        switch(type){

            case "String":
                return "\"" + value + "\"";

            case "int":
            case "long":
            case "double":
            case "boolean":
                return value;

            case "int[]":

                return "new int[]{"
                        +
                        value
                                .replace(
                                        "[",
                                        ""
                                )
                                .replace(
                                        "]",
                                        ""
                                )
                        +
                        "}";

            case "int[][]":

                return "new int[][]"
                        +
                        value
                                .replace(
                                        "[[",
                                        "{{"
                                )
                                .replace(
                                        "]]",
                                        "}}"
                                )
                                .replace(
                                        "],[",
                                        "},{"
                                );

            default:
                throw new RuntimeException(
                        "Unsupported type: "
                                + type
                );

        }

    }

}
