package com.leetcode.backend.methodsignature.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MethodParameter {

    private String type;

    private String name;
}
