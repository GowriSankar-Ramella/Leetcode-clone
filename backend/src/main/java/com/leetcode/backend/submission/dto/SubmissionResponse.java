package com.leetcode.backend.submission.dto;

import com.leetcode.backend.execution.enums.ExecutionMode;
import com.leetcode.backend.problem.enums.Language;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmissionResponse {

    private Long id;

    private Long problemId;

    private Language language;

    private ExecutionMode mode;

    private Integer passed;

    private Integer total;

    private String status;

    private LocalDateTime createdAt;

}
