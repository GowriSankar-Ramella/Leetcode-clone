package com.leetcode.backend.methodsignature.dto;

import com.leetcode.backend.methodsignature.entity.MethodParameter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateMethodSignatureRequest {

    @NotBlank
    private String methodName;

    @NotBlank
    private String returnType;

    @NotEmpty
    private List<MethodParameter> parameters;
}
