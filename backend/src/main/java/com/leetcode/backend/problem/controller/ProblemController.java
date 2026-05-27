package com.leetcode.backend.problem.controller;
import com.leetcode.backend.problem.dto.CreateProblemRequest;
import com.leetcode.backend.problem.dto.ProblemResponse;
import com.leetcode.backend.problem.dto.UpdateProblemRequest;
import com.leetcode.backend.problem.service.ProblemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/problems")
@RequiredArgsConstructor
public class ProblemController {

    private final ProblemService problemService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProblemResponse createProblem(
            @Valid @RequestBody
            CreateProblemRequest request
    ) {

        return problemService.createProblem(request);
    }

    @GetMapping
    public List<ProblemResponse> getAllProblems() {

        return problemService.getAllProblems();
    }

    @GetMapping("/{id}")
    public ProblemResponse getProblemById(
            @PathVariable Long id
    ) {

        return problemService.getProblemById(id);
    }

    @PutMapping("/{id}")
    public ProblemResponse updateProblem(
            @PathVariable Long id,
            @Valid @RequestBody
            UpdateProblemRequest request
    ) {

        return problemService.updateProblem(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProblem(
            @PathVariable Long id
    ) {

        problemService.deleteProblem(id);
    }
}
