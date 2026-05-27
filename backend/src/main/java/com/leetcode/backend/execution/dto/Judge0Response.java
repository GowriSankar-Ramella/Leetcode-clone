package com.leetcode.backend.execution.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Judge0Response {

    private String stdout;

    private String stderr;

    private String compile_output;

    private String message;

    private Status status;

    @Getter
    @Setter
    public static class Status {
        private Integer id;
        private String description;
    }
}
