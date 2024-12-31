package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.review.request.CreateReviewRequestDto;
import com.korit.projectrrs.dto.review.request.UpdateReviewRequestDto;
import com.korit.projectrrs.dto.review.response.GetAvgReviewScoreResponseDto;
import com.korit.projectrrs.dto.review.response.GetReviewResponseDto;
import com.korit.projectrrs.dto.review.response.CreateReviewResponseDto;
import com.korit.projectrrs.dto.review.response.UpdateReviewResponseDto;

import java.util.List;

public interface ReviewService {
    ResponseDto<CreateReviewResponseDto> createReview(Long userId, CreateReviewRequestDto dto);
    ResponseDto<List<GetReviewResponseDto>> getReviewsByProvider(Long providerId);
    ResponseDto<GetAvgReviewScoreResponseDto> getAverageReviewScoreByProvider(Long userId);
    ResponseDto<GetReviewResponseDto> getByReviewId(Long reviewId);
    ResponseDto<GetReviewResponseDto> getLatestReviewByProviderId(Long providerId);
    ResponseDto<UpdateReviewResponseDto> updateReview(Long reviewId, Long reservationId, UpdateReviewRequestDto dto);
    ResponseDto<Void> deleteReview(Long reviewId);
}
