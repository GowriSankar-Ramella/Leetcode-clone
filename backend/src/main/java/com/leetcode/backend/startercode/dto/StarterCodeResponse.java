package com.leetcode.backend.startercode.dto;

import com.leetcode.backend.problem.enums.Language;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StarterCodeResponse {

    private Long id;

    private Language language;

    private String starterCode;
}
