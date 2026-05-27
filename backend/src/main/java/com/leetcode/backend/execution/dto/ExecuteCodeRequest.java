package com.leetcode.backend.execution.dto;

import com.leetcode.backend.execution.enums.ExecutionMode;
import com.leetcode.backend.problem.enums.Language;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExecuteCodeRequest {

    @NotNull
    private Long problemId;

    @NotNull
    private Language language;

    @NotBlank
    private String code;

    private ExecutionMode mode;
}
