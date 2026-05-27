package com.leetcode.backend.testcases.repository;

import com.leetcode.backend.testcases.entity.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestCaseRepository extends JpaRepository<TestCase, Long> {
    List<TestCase> findByProblemId(Long problemId);

    List<TestCase> findByProblemIdAndHidden(
            Long problemId,
            boolean hidden
    );
    List<TestCase> findByProblemIdAndHiddenFalse(
            Long problemId
    );
}
