package com.leetcode.backend.testcases.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTestCaseRequest {

    @NotBlank
    private String input;

    @NotBlank
    private String expectedOutput;

    private boolean hidden;
}
