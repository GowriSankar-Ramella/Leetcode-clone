package com.leetcode.backend.submission;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmissionRepository
        extends JpaRepository<
                Submission,
                Long
                > {
    List<Submission> findByProblemId(
            Long problemId
    );

    List<Submission> findByProblemIdOrderByCreatedAtDesc(
            Long problemId
    );

    List<Submission> findByUserIdOrderByCreatedAtDesc(
            Long userId
    );

    Long countByUserId(
            Long userId
    );

    Long countByUserIdAndStatus(
            Long userId,
            String status
    );

}
