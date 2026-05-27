package com.leetcode.backend.methodsignature.entity;
import com.leetcode.backend.problem.entity.Problem;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "method_signatures")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MethodSignature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String methodName;

    @Column(nullable = false)
    private String returnType;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String parameters;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_id", nullable = false, unique = true)
    private Problem problem;
}
