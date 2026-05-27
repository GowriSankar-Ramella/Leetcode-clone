package com.leetcode.backend.startercode.repository;

import com.leetcode.backend.problem.enums.Language;
import com.leetcode.backend.startercode.entity.StarterCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StarterCodeRepository extends JpaRepository<StarterCode, Long> {
    List<StarterCode> findByProblemId(Long problemId);

    Optional<StarterCode> findByProblemIdAndLanguage(
            Long problemId,
            Language language
    );
}
