package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.review.request.ReviewPostRequestDto;
import com.korit.projectrrs.dto.review.request.ReviewPutRequestDto;
import com.korit.projectrrs.dto.review.response.ReviewAvgScoreResponseDto;
import com.korit.projectrrs.dto.review.response.ReviewGetResponseDto;
import com.korit.projectrrs.dto.review.response.ReviewPostResponseDto;
import com.korit.projectrrs.dto.review.response.ReviewPutResponseDto;

import java.util.List;

public interface ReviewService {
    ResponseDto<ReviewPostResponseDto> createReview(String userId, ReviewPostRequestDto dto);
    ResponseDto<List<ReviewGetResponseDto>> getReviewsByProvider(Long providerId);
    ResponseDto<ReviewAvgScoreResponseDto> getAverageReviewScoreByProvider(Long providerId);
    ResponseDto<ReviewGetResponseDto> getByReviewId(Long reviewId);
    ResponseDto<ReviewPutResponseDto> updateReview(ReviewPutRequestDto dto);
    ResponseDto<Void> deleteReview(Long reviewId);

}
