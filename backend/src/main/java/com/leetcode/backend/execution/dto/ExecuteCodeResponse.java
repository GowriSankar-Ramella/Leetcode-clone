package com.leetcode.backend.execution.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExecuteCodeResponse {

    private Integer passed;

    private Integer total;

    private String status;

    private String failedInput;

    private String expected;

    private String actual;

    private String error;
}
