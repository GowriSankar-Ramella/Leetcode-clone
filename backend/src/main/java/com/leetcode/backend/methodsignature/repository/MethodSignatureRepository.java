package com.leetcode.backend.methodsignature.repository;

import com.leetcode.backend.methodsignature.entity.MethodSignature;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MethodSignatureRepository extends JpaRepository<MethodSignature, Long> {
    Optional<MethodSignature> findByProblemId(
            Long problemId
    );
}
