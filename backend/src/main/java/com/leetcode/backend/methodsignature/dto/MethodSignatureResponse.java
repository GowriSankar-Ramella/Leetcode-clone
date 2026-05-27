package com.leetcode.backend.methodsignature.dto;

import com.leetcode.backend.methodsignature.entity.MethodParameter;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MethodSignatureResponse {

    private Long id;

    private String methodName;

    private String returnType;

    private List<MethodParameter> parameters;
}
