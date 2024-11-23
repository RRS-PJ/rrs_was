package com.korit.projectrrs.controller;

import com.korit.projectrrs.common.ApiMappingPattern;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.review.request.ReviewPostRequestDto;
import com.korit.projectrrs.dto.review.response.ReviewGetResponseDto;
import com.korit.projectrrs.dto.review.response.ReviewPostResponseDto;
import com.korit.projectrrs.dto.review.response.ReviewPutResponseDto;
import com.korit.projectrrs.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.REVIEW)
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    private ResponseEntity<ResponseDto<ReviewPostResponseDto>> creaeteReview (
            @AuthenticationPrincipal String userId,
            @RequestBody ReviewPostRequestDto dto
            ){
        ResponseDto<ReviewPostResponseDto> response = reviewService.createReview(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping
    private ResponseEntity<ResponseDto<List<ReviewGetResponseDto>>> getAllReviewByProviderId (
            @AuthenticationPrincipal String userId,
            @PathVariable Long providerId
    ) {
        ResponseDto<List<ReviewGetResponseDto>> response = reviewService.getAllReviewByProviderId(providerId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping
    private ResponseEntity<ResponseDto<ReviewGetResponseDto>> getReviewByUserId (
            @AuthenticationPrincipal String userId
    ) {
        ResponseDto<ReviewGetResponseDto> response = reviewService.getReviewByUserId(userId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping
    private ResponseEntity<ResponseDto<ReviewPutResponseDto>> updateReview (
            @AuthenticationPrincipal String userId,
            @RequestBody ReviewPutResponseDto dto
    ){
        ResponseDto<ReviewPutResponseDto> response = reviewService.updateReview(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping
    ResponseEntity<ResponseDto<Void>> deleteReview (
            @AuthenticationPrincipal String userId,
            @RequestParam Long reviewId
    ){
        ResponseDto<ReviewPutResponseDto> response = reviewService.deleteReview(reviewId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.FORBIDDEN;
        return ResponseEntity.status(status).body(null);
    }
}