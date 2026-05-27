package com.leetcode.backend.submission.service;

import com.leetcode.backend.security.service.AuthService;
import com.leetcode.backend.submission.SubmissionRepository;
import com.leetcode.backend.submission.dto.SubmissionResponse;
import com.leetcode.backend.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubmissionService {

    private final SubmissionRepository
            submissionRepository;

    private final AuthService
            authService;

    public List<SubmissionResponse>
    getProblemSubmissions(
            Long problemId
    ){

        return submissionRepository
                .findByProblemIdOrderByCreatedAtDesc(
                        problemId
                )
                .stream()
                .map(
                        submission ->
                                SubmissionResponse
                                        .builder()
                                        .id(
                                                submission.getId()
                                        )
                                        .problemId(
                                                submission.getProblemId()
                                        )
                                        .language(
                                                submission.getLanguage()
                                        )
                                        .mode(
                                                submission.getMode()
                                        )
                                        .passed(
                                                submission.getPassed()
                                        )
                                        .total(
                                                submission.getTotal()
                                        )
                                        .status(
                                                submission.getStatus()
                                        )
                                        .createdAt(
                                                submission.getCreatedAt()
                                        )
                                        .build()
                )
                .toList();

    }

    public List<SubmissionResponse>
    getMySubmissions(){

        User currentUser =
                authService
                        .getCurrentUser();

        return submissionRepository
                .findByUserIdOrderByCreatedAtDesc(
                        currentUser.getId()
                )
                .stream()
                .map(
                        submission ->
                                SubmissionResponse
                                        .builder()
                                        .id(
                                                submission.getId()
                                        )
                                        .problemId(
                                                submission.getProblemId()
                                        )
                                        .language(
                                                submission.getLanguage()
                                        )
                                        .mode(
                                                submission.getMode()
                                        )
                                        .passed(
                                                submission.getPassed()
                                        )
                                        .total(
                                                submission.getTotal()
                                        )
                                        .status(
                                                submission.getStatus()
                                        )
                                        .createdAt(
                                                submission.getCreatedAt()
                                        )
                                        .build()
                )
                .toList();

    }

}
