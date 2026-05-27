package com.leetcode.backend.startercode.controller;

import com.leetcode.backend.problem.enums.Language;
import com.leetcode.backend.startercode.dto.CreateStarterCodeRequest;
import com.leetcode.backend.startercode.dto.StarterCodeResponse;
import com.leetcode.backend.startercode.service.StarterCodeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StarterCodeController {

    private final StarterCodeService starterCodeService;

    @PostMapping("/problems/{problemId}/starter-codes")
    @ResponseStatus(HttpStatus.CREATED)
    public StarterCodeResponse createStarterCode(
            @PathVariable Long problemId,
            @Valid @RequestBody
            CreateStarterCodeRequest request
    ) {

        return starterCodeService.createStarterCode(
                problemId,
                request
        );
    }

    @GetMapping("/problems/{problemId}/starter-codes")
    public List<StarterCodeResponse> getStarterCodes(
            @PathVariable Long problemId
    ) {

        return starterCodeService.getStarterCodesByProblem(
                problemId
        );
    }

    @GetMapping(
            "/problems/{problemId}/starter-codes/{language}"
    )
    public StarterCodeResponse getStarterCodeByLanguage(
            @PathVariable Long problemId,
            @PathVariable Language language
    ) {

        return starterCodeService.getStarterCodeByLanguage(
                problemId,
                language
        );
    }

    @DeleteMapping("/starter-codes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStarterCode(
            @PathVariable Long id
    ) {

        starterCodeService.deleteStarterCode(id);
    }
}
