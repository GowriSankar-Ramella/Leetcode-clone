package com.leetcode.backend.problem.service;
import com.leetcode.backend.common.exception.ResourceNotFoundException;
import com.leetcode.backend.problem.dto.CreateProblemRequest;
import com.leetcode.backend.problem.dto.ProblemResponse;
import com.leetcode.backend.problem.dto.UpdateProblemRequest;
import com.leetcode.backend.problem.entity.Problem;
import com.leetcode.backend.problem.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProblemService {

    private final ProblemRepository problemRepository;

    public ProblemResponse createProblem(
            CreateProblemRequest request
    ) {

        Problem problem = Problem.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .difficulty(request.getDifficulty())
                .build();

        Problem savedProblem = problemRepository.save(problem);

        return mapToResponse(savedProblem);
    }

    public List<ProblemResponse> getAllProblems() {

        List<Problem> problems = problemRepository.findAll();

        return problems.stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ProblemResponse getProblemById(Long id) {

        Problem problem = problemRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Problem not found with id: " + id
                        )
                );

        return mapToResponse(problem);
    }

    public ProblemResponse updateProblem(
            Long id,
            UpdateProblemRequest request
    ) {

        Problem problem = problemRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Problem not found with id: " + id
                        )
                );

        problem.setTitle(request.getTitle());
        problem.setDescription(request.getDescription());
        problem.setDifficulty(request.getDifficulty());

        Problem updatedProblem = problemRepository.save(problem);

        return mapToResponse(updatedProblem);
    }

    public void deleteProblem(Long id) {

        Problem problem = problemRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Problem not found with id: " + id
                        )
                );

        problemRepository.delete(problem);
    }

    private ProblemResponse mapToResponse(
            Problem problem
    ) {

        return ProblemResponse.builder()
                .id(problem.getId())
                .title(problem.getTitle())
                .description(problem.getDescription())
                .difficulty(problem.getDifficulty())
                .build();
    }
}
