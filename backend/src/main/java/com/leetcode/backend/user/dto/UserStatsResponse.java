package com.leetcode.backend.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserStatsResponse {

    private Long totalSubmissions;

    private Long acceptedSubmissions;

    private Double acceptanceRate;

}
