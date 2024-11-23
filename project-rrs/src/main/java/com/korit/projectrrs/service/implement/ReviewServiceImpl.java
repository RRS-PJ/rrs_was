package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.review.request.ReviewPostRequestDto;
import com.korit.projectrrs.dto.review.response.ReviewGetResponseDto;
import com.korit.projectrrs.dto.review.response.ReviewPostResponseDto;
import com.korit.projectrrs.dto.review.response.ReviewPutResponseDto;
import com.korit.projectrrs.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    @Override
    public ResponseDto<ReviewPostResponseDto> createReview(ReviewPostRequestDto dto) {
        ReviewPostResponseDto data = null;

        return null;
    }

    @Override
    public ResponseDto<List<ReviewGetResponseDto>> getAllReviewByProviderId(Long providerId) {
        List<ReviewGetResponseDto> data = null;
        return null;
    }

    @Override
    public ResponseDto<ReviewGetResponseDto> getReviewByUserId(String userId) {
        ReviewGetResponseDto data = null;
        return null;
    }

    @Override
    public ResponseDto<ReviewPutResponseDto> updateReview(ReviewPutResponseDto dto) {
        ReviewPutResponseDto data = null;
        return null;
    }

    @Override
    public ResponseDto<ReviewPutResponseDto> deleteReview(Long reviewId) {
        try {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
