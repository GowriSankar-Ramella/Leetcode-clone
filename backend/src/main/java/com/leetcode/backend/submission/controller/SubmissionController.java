package com.leetcode.backend.submission.controller;

import com.leetcode.backend.submission.dto.SubmissionResponse;
import com.leetcode.backend.submission.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/submissions")
@RequiredArgsConstructor
public class SubmissionController {

    private final SubmissionService
            submissionService;

    @GetMapping(
            "/problem/{problemId}"
    )
    public List<SubmissionResponse>
    getSubmissions(
            @PathVariable
            Long problemId
    ){

        return submissionService
                .getProblemSubmissions(
                        problemId
                );

    }

    @GetMapping(
            "/my-history"
    )
    public List<SubmissionResponse>
    myHistory(){

        return submissionService
                .getMySubmissions();

    }

}
