package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.review.request.ReviewPostRequestDto;
import com.korit.projectrrs.dto.review.response.ReviewGetResponseDto;
import com.korit.projectrrs.dto.review.response.ReviewPostResponseDto;
import com.korit.projectrrs.dto.review.response.ReviewPutResponseDto;

import java.util.List;

public interface ReviewService {
    ResponseDto<ReviewPostResponseDto> createReview(ReviewPostRequestDto dto);
    ResponseDto<List<ReviewGetResponseDto>> getAllReviewByProviderId(Long providerId);
    ResponseDto<ReviewGetResponseDto> getReviewByUserId(String userId);
    ResponseDto<ReviewPutResponseDto> updateReview(ReviewPutResponseDto dto);
    ResponseDto<ReviewPutResponseDto> deleteReview(Long reviewId);
}
