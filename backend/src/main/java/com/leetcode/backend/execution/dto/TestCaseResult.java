package com.leetcode.backend.execution.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TestCaseResult {

    private Integer passed;

    private Integer total;

    private String status;

    private String failedInput;

    private String expected;

    private String actual;

}
