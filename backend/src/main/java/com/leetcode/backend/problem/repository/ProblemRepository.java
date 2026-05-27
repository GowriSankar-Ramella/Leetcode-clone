package com.leetcode.backend.problem.repository;

import com.leetcode.backend.problem.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
}
