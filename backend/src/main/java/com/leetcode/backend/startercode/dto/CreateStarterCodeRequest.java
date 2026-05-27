package com.leetcode.backend.startercode.dto;

import com.leetcode.backend.problem.enums.Language;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateStarterCodeRequest {

    @NotNull
    private Language language;

    @NotBlank
    private String starterCode;
}
