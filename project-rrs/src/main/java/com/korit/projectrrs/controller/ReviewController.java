package com.korit.projectrrs.controller;

import com.korit.projectrrs.common.ApiMappingPattern;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.review.request.CreateReviewRequestDto;
import com.korit.projectrrs.dto.review.request.UpdateReviewRequestDto;
import com.korit.projectrrs.dto.review.response.GetAvgReviewScoreResponseDto;
import com.korit.projectrrs.dto.review.response.GetReviewResponseDto;
import com.korit.projectrrs.dto.review.response.CreateReviewResponseDto;
import com.korit.projectrrs.dto.review.response.UpdateReviewResponseDto;
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
    private static final String LATEST_REVIEW_BY_PROVIDER = "/latest-review/{providerId}";
    private static final String PROVIDER_AVG = "/provider-avg/{providerId}";

    @PostMapping
    private ResponseEntity<ResponseDto<CreateReviewResponseDto>> createReview (
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestBody CreateReviewRequestDto dto
            ){
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<CreateReviewResponseDto> response = reviewService.createReview(userId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(PROVIDER)
    private ResponseEntity<ResponseDto<List<GetReviewResponseDto>>> getReviewsByProvider (
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long providerId
    ) {
        ResponseDto<List<GetReviewResponseDto>> response = reviewService.getReviewsByProvider(providerId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(PROVIDER_AVG)
    private ResponseEntity<ResponseDto<GetAvgReviewScoreResponseDto>> getAverageReviewScoreByProvider (
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long providerId
    ) {
        ResponseDto<GetAvgReviewScoreResponseDto> response = reviewService.getAverageReviewScoreByProvider(providerId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping("reservation/{reservationId}"+REVIEW)
    private ResponseEntity<ResponseDto<GetReviewResponseDto>> getReviewByReviewId (
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long reviewId
    ) {
        ResponseDto<GetReviewResponseDto> response = reviewService.getByReviewId(reviewId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(LATEST_REVIEW_BY_PROVIDER)
    private ResponseEntity<ResponseDto<GetReviewResponseDto>> getLatestReviewByProviderId (
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long providerId
    ) {
        ResponseDto<GetReviewResponseDto> response = reviewService.getLatestReviewByProviderId(providerId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping("reservation/{reservationId}"+REVIEW)
    private ResponseEntity<ResponseDto<UpdateReviewResponseDto>> updateReview (
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long reviewId,
            @PathVariable Long reservationId,
            @RequestBody UpdateReviewRequestDto dto
    ){
        ResponseDto<UpdateReviewResponseDto> response = reviewService.updateReview(reviewId, reservationId, dto);
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