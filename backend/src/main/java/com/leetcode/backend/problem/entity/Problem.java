package com.leetcode.backend.problem.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leetcode.backend.methodsignature.entity.MethodSignature;
import com.leetcode.backend.problem.enums.Difficulty;
import com.leetcode.backend.startercode.entity.StarterCode;
import com.leetcode.backend.testcases.entity.TestCase;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "problems")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Difficulty difficulty;
    @OneToMany(
            mappedBy = "problem",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<StarterCode> starterCodes = new ArrayList<>();

    @OneToMany(
            mappedBy = "problem",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<TestCase> testCases = new ArrayList<>();

    @OneToOne(
            mappedBy = "problem",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private MethodSignature methodSignature;

}
