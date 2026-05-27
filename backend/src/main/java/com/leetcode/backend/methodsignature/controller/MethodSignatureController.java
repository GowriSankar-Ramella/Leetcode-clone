package com.leetcode.backend.methodsignature.controller;
import com.leetcode.backend.methodsignature.dto.CreateMethodSignatureRequest;
import com.leetcode.backend.methodsignature.dto.MethodSignatureResponse;
import com.leetcode.backend.methodsignature.service.MethodSignatureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MethodSignatureController {

    private final MethodSignatureService methodSignatureService;

    @PostMapping("/problems/{problemId}/method-signature")
    @ResponseStatus(HttpStatus.CREATED)
    public MethodSignatureResponse createMethodSignature(
            @PathVariable Long problemId,
            @Valid @RequestBody
            CreateMethodSignatureRequest request
    ) {

        return methodSignatureService.createMethodSignature(
                problemId,
                request
        );
    }

    @GetMapping("/problems/{problemId}/method-signature")
    public MethodSignatureResponse getMethodSignature(
            @PathVariable Long problemId
    ) {

        return methodSignatureService.getMethodSignature(
                problemId
        );
    }

    @PutMapping("/problems/{problemId}/method-signature")
    public MethodSignatureResponse updateMethodSignature(
            @PathVariable Long problemId,
            @Valid @RequestBody
            CreateMethodSignatureRequest request
    ) {

        return methodSignatureService.updateMethodSignature(
                problemId,
                request
        );
    }

}
