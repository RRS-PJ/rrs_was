package com.korit.projectrrs.controller;

import com.korit.projectrrs.common.ApiMappingPattern;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.review.request.ReviewPostRequestDto;
import com.korit.projectrrs.dto.review.request.ReviewPutRequestDto;
import com.korit.projectrrs.dto.review.response.ReviewAvgScoreResponseDto;
import com.korit.projectrrs.dto.review.response.ReviewGetResponseDto;
import com.korit.projectrrs.dto.review.response.ReviewPostResponseDto;
import com.korit.projectrrs.dto.review.response.ReviewPutResponseDto;
import com.korit.projectrrs.security.PrincipalUser;
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
    private static final String REVIEW = "/{reviewId}";
    private static final String PROVIDER = "/provider/{providerId}";
    private static final String PROVIDER_AVG = "/provider-avg/{providerId}";


    @PostMapping
    private ResponseEntity<ResponseDto<ReviewPostResponseDto>> createReview (
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestBody ReviewPostRequestDto dto
            ){
        ResponseDto<ReviewPostResponseDto> response = reviewService.createReview(principalUser, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(PROVIDER)
    private ResponseEntity<ResponseDto<List<ReviewGetResponseDto>>> getReviewsByProvider (
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long providerId
    ) {
        ResponseDto<List<ReviewGetResponseDto>> response = reviewService.getReviewsByProvider(providerId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(PROVIDER_AVG)
    private ResponseEntity<ResponseDto<ReviewAvgScoreResponseDto>> getAverageReviewScoreByProvider (
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long providerId
    ) {
        ResponseDto<ReviewAvgScoreResponseDto> response = reviewService.getAverageReviewScoreByProvider(providerId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(REVIEW)
    private ResponseEntity<ResponseDto<ReviewGetResponseDto>> getByReviewId (
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long reviewId
    ) {
        ResponseDto<ReviewGetResponseDto> response = reviewService.getByReviewId(reviewId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping(REVIEW)
    private ResponseEntity<ResponseDto<ReviewPutResponseDto>> updateReview (
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestBody ReviewPutRequestDto dto
    ){
        ResponseDto<ReviewPutResponseDto> response = reviewService.updateReview(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping(REVIEW)
    ResponseEntity<ResponseDto<Void>> deleteReview (
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long reviewId
    ){
        ResponseDto<Void> response = reviewService.deleteReview(reviewId);
        HttpStatus status = response.isResult() ? HttpStatus.NO_CONTENT : HttpStatus.FORBIDDEN;
        return ResponseEntity.status(status).body(null);
    }
}