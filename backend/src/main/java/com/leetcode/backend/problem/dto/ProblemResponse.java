package com.leetcode.backend.problem.dto;

import com.leetcode.backend.problem.enums.Difficulty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProblemResponse {

    private Long id;

    private String title;

    private String description;

    private Difficulty difficulty;
}