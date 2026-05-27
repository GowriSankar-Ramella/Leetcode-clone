package com.leetcode.backend.testcases.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestCaseResponse {

    private Long id;

    private String input;

    private String expectedOutput;

    private boolean hidden;
}
