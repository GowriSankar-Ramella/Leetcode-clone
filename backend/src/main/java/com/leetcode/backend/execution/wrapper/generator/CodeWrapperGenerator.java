package com.leetcode.backend.execution.wrapper.generator;

import com.leetcode.backend.testcases.entity.TestCase;

import java.util.List;

public interface CodeWrapperGenerator {

    String generate(
            Long problemId,
            String userCode,
            List<TestCase> testCases
    );

}
