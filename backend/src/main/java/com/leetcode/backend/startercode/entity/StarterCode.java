package com.leetcode.backend.startercode.entity;
import com.leetcode.backend.problem.entity.Problem;
import com.leetcode.backend.problem.enums.Language;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "starter_codes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StarterCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Language language;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String starterCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_id", nullable = false)
    private Problem problem;
}
