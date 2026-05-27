package com.leetcode.backend.submission;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leetcode.backend.execution.enums.ExecutionMode;
import com.leetcode.backend.problem.enums.Language;
import com.leetcode.backend.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Submission {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    private Long problemId;

    @Enumerated(
            EnumType.STRING
    )
    private Language language;

    @Enumerated(
            EnumType.STRING
    )
    private ExecutionMode mode;

    @Column(
            columnDefinition = "TEXT"
    )
    private String code;

    private Integer passed;

    private Integer total;

    private String status;

    @Column(
            columnDefinition = "TEXT"
    )
    private String error;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(
            name="user_id"
    )
    @JsonIgnore
    private User user;

}
