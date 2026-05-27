package com.leetcode.backend.methodsignature.service;

import com.leetcode.backend.common.exception.ResourceNotFoundException;
import com.leetcode.backend.methodsignature.dto.CreateMethodSignatureRequest;
import com.leetcode.backend.methodsignature.dto.MethodSignatureResponse;
import com.leetcode.backend.methodsignature.entity.MethodParameter;
import com.leetcode.backend.methodsignature.entity.MethodSignature;
import com.leetcode.backend.methodsignature.repository.MethodSignatureRepository;
import com.leetcode.backend.problem.entity.Problem;
import com.leetcode.backend.problem.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MethodSignatureService {

    private final MethodSignatureRepository methodSignatureRepository;
    private final ProblemRepository problemRepository;
    private final ObjectMapper objectMapper;

    public MethodSignatureResponse createMethodSignature(
            Long problemId,
            CreateMethodSignatureRequest request
    ) {

        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Problem not found with id: " + problemId
                        )
                );

        String parametersJson = convertParametersToJson(
                request.getParameters()
        );

        MethodSignature methodSignature =
                MethodSignature.builder()
                        .methodName(request.getMethodName())
                        .returnType(request.getReturnType())
                        .parameters(parametersJson)
                        .problem(problem)
                        .build();

        MethodSignature savedMethodSignature =
                methodSignatureRepository.save(methodSignature);

        return mapToResponse(savedMethodSignature);
    }

    public MethodSignatureResponse getMethodSignature(
            Long problemId
    ) {

        MethodSignature methodSignature =
                methodSignatureRepository
                        .findByProblemId(problemId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Method signature not found"
                                )
                        );

        return mapToResponse(methodSignature);
    }

    public MethodSignatureResponse updateMethodSignature(
            Long problemId,
            CreateMethodSignatureRequest request
    ) {

        MethodSignature methodSignature =
                methodSignatureRepository
                        .findByProblemId(problemId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Method signature not found"
                                )
                        );

        methodSignature.setMethodName(
                request.getMethodName()
        );

        methodSignature.setReturnType(
                request.getReturnType()
        );

        methodSignature.setParameters(
                convertParametersToJson(
                        request.getParameters()
                )
        );

        MethodSignature updatedMethodSignature =
                methodSignatureRepository.save(methodSignature);

        return mapToResponse(updatedMethodSignature);
    }

    private MethodSignatureResponse mapToResponse(
            MethodSignature methodSignature
    ) {

        return MethodSignatureResponse.builder()
                .id(methodSignature.getId())
                .methodName(methodSignature.getMethodName())
                .returnType(methodSignature.getReturnType())
                .parameters(
                        convertJsonToParameters(
                                methodSignature.getParameters()
                        )
                )
                .build();
    }

    private String convertParametersToJson(
            List<MethodParameter> parameters
    ) {

        try {

            return objectMapper.writeValueAsString(parameters);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to serialize parameters"
            );
        }
    }

    private List<MethodParameter> convertJsonToParameters(
            String json
    ) {

        try {

            return objectMapper.readValue(
                    json,
                    new TypeReference<List<MethodParameter>>() {}
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to deserialize parameters"
            );
        }
    }
}
