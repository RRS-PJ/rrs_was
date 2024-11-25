package com.korit.projectrrs.controller;

import com.korit.projectrrs.common.ApiMappingPattern;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.review.request.ReviewPostRequestDto;
import com.korit.projectrrs.dto.review.response.ReviewAvgScoreResponseDto;
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
    private final String GET_REVIEW = "/{reviewId}";
    private final String DELETE_REVIEW = "/{reviewId}";
    private final String UPDATE_REVIEW = "/{reviewId}";
    private final String GET_REVIEW_BY_PROVIDER = "/provider/{providerId}";
    private final String GET_REVIEW_AVG_SCORE_BY_PROVIDER = "/provider-avg/{providerId}";


    @PostMapping
    private ResponseEntity<ResponseDto<ReviewPostResponseDto>> createReview (
            @AuthenticationPrincipal String userId,
            @RequestBody ReviewPostRequestDto dto
            ){
        ResponseDto<ReviewPostResponseDto> response = reviewService.createReview(userId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(GET_REVIEW_BY_PROVIDER)
    private ResponseEntity<ResponseDto<List<ReviewGetResponseDto>>> getAllReviewByProviderId (
            @AuthenticationPrincipal String userId,
            @PathVariable Long providerId
    ) {
        ResponseDto<List<ReviewGetResponseDto>> response = reviewService.getReviewsByProvider(providerId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(GET_REVIEW_AVG_SCORE_BY_PROVIDER)
    private ResponseEntity<ResponseDto<ReviewAvgScoreResponseDto>> getAverageReviewScoreByProvider (
            @AuthenticationPrincipal String userId,
            @PathVariable Long providerId
    ) {
        ResponseDto<ReviewAvgScoreResponseDto> response = reviewService.getAverageReviewScoreByProvider(providerId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(GET_REVIEW)
    private ResponseEntity<ResponseDto<ReviewGetResponseDto>> getByReviewId (
            @AuthenticationPrincipal String userId,
            @RequestParam Long reviewId
    ) {
        ResponseDto<ReviewGetResponseDto> response = reviewService.getByReviewId(reviewId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping(UPDATE_REVIEW)
    private ResponseEntity<ResponseDto<ReviewPutResponseDto>> updateReview (
            @AuthenticationPrincipal String userId,
            @RequestBody ReviewPutResponseDto dto
    ){
        ResponseDto<ReviewPutResponseDto> response = reviewService.updateReview(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping(DELETE_REVIEW)
    ResponseEntity<ResponseDto<Void>> deleteReview (
            @AuthenticationPrincipal String userId,
            @RequestParam Long reviewId
    ){
        ResponseDto<ReviewPutResponseDto> response = reviewService.deleteReview(reviewId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.FORBIDDEN;
        return ResponseEntity.status(status).body(null);
    }
}