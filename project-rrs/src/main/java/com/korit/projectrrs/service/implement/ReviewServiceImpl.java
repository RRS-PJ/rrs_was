package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.common.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.review.request.ReviewPostRequestDto;
import com.korit.projectrrs.dto.review.response.ReviewGetResponseDto;
import com.korit.projectrrs.dto.review.response.ReviewPostResponseDto;
import com.korit.projectrrs.dto.review.response.ReviewPutResponseDto;
import com.korit.projectrrs.entity.Review;
import com.korit.projectrrs.repositoiry.ReviewRepository;
import com.korit.projectrrs.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    @Override
    public ResponseDto<ReviewPostResponseDto> createReview(ReviewPostRequestDto dto) {
        ReviewPostResponseDto data = null;
        int score = dto.getReviewScore();
        String content = dto.getReviewContent();

        //유효성 검사
        if(content == null || content.isEmpty() || content.length() > 500) {
            return ResponseDto.setFailed(ResponseMessage.REVIEW_TOO_lONG);
        }

        try {
            Review review = Review.builder()
                    .reviewScore(score)
                    .reviewContent(content)
                    .reviewCreateAt(LocalDate.now())
                    .build();

            reviewRepository.save(review);

            data = new ReviewPostResponseDto(review);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<List<ReviewGetResponseDto>> getAllReviewByProviderId(Long providerId) {
        List<ReviewGetResponseDto> data = null;

        try {
            Optional<List<Review>> reviews = reviewRepository.findAllByProiderId(providerId);
            if (reviews.isPresent()) {
                data = reviews.get().stream()
                        .map(ReviewGetResponseDto::new)
                        .collect(Collectors.toList());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<ReviewGetResponseDto> getByReviewId(Long reviewId) {
        return null;
    }

    @Override
    public ResponseDto<ReviewPutResponseDto> updateReview(ReviewPutResponseDto dto) {
        ReviewPutResponseDto data = null;

        try {

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<ReviewPutResponseDto> deleteReview(Long reviewId) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }
}