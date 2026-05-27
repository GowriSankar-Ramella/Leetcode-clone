package com.leetcode.backend.startercode.service;

import com.leetcode.backend.common.exception.ResourceNotFoundException;
import com.leetcode.backend.problem.entity.Problem;
import com.leetcode.backend.problem.enums.Language;
import com.leetcode.backend.problem.repository.ProblemRepository;
import com.leetcode.backend.startercode.dto.CreateStarterCodeRequest;
import com.leetcode.backend.startercode.dto.StarterCodeResponse;
import com.leetcode.backend.startercode.entity.StarterCode;
import com.leetcode.backend.startercode.repository.StarterCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StarterCodeService {

    private final StarterCodeRepository starterCodeRepository;
    private final ProblemRepository problemRepository;

    public StarterCodeResponse createStarterCode(
            Long problemId,
            CreateStarterCodeRequest request
    ) {

        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Problem not found with id: " + problemId
                        )
                );

        StarterCode starterCode = StarterCode.builder()
                .language(request.getLanguage())
                .starterCode(request.getStarterCode())
                .problem(problem)
                .build();

        StarterCode savedStarterCode =
                starterCodeRepository.save(starterCode);

        return mapToResponse(savedStarterCode);
    }

    public List<StarterCodeResponse> getStarterCodesByProblem(
            Long problemId
    ) {

        return starterCodeRepository.findByProblemId(problemId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public StarterCodeResponse getStarterCodeByLanguage(
            Long problemId,
            Language language
    ) {

        StarterCode starterCode =
                starterCodeRepository
                        .findByProblemIdAndLanguage(
                                problemId,
                                language
                        )
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Starter code not found"
                                )
                        );

        return mapToResponse(starterCode);
    }

    public void deleteStarterCode(Long id) {

        StarterCode starterCode =
                starterCodeRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Starter code not found"
                                )
                        );

        starterCodeRepository.delete(starterCode);
    }

    private StarterCodeResponse mapToResponse(
            StarterCode starterCode
    ) {

        return StarterCodeResponse.builder()
                .id(starterCode.getId())
                .language(starterCode.getLanguage())
                .starterCode(starterCode.getStarterCode())
                .build();
    }
}
