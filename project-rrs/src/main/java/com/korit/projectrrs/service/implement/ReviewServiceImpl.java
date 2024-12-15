package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.common.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.review.request.CreateReviewRequestDto;
import com.korit.projectrrs.dto.review.request.UpdateReviewRequestDto;
import com.korit.projectrrs.dto.review.response.GetAvgReviewScoreResponseDto;
import com.korit.projectrrs.dto.review.response.GetReviewResponseDto;
import com.korit.projectrrs.dto.review.response.CreateReviewResponseDto;
import com.korit.projectrrs.dto.review.response.UpdateReviewResponseDto;
import com.korit.projectrrs.entity.Reservation;
import com.korit.projectrrs.entity.ReservationStatus;
import com.korit.projectrrs.entity.Review;
import com.korit.projectrrs.entity.User;
import com.korit.projectrrs.repositoiry.ReservationRepository;
import com.korit.projectrrs.repositoiry.ReviewRepository;
import com.korit.projectrrs.repositoiry.UserRepository;
import com.korit.projectrrs.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.InternalException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public ResponseDto<CreateReviewResponseDto> createReview(Long userId, @Valid CreateReviewRequestDto dto)  {
        CreateReviewResponseDto data = null;
        Long providerId = dto.getProviderId();
        int score = dto.getReviewScore();
        String content = dto.getReviewContent();
        Long reservationId = dto.getReservationId();

        //유효성 검사
        if (content == null || content.isEmpty() || content.length() > 500) {
            return ResponseDto.setFailed(ResponseMessage.REVIEW_TOO_lONG);
        }
        if (score > 5 || score < 0) {
            return ResponseDto.setFailed(ResponseMessage.REVIEW_SCORE_NUMBER_VALIDATION);
        }

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new InternalException(ResponseMessage.NOT_EXIST_RESERVATION));

        if (reservation.getReservationStatus() != ReservationStatus.COMPLETED){
            return ResponseDto.setFailed(ResponseMessage.RESERVATION_IS_NOT_COMPLETED);
        }

        try{
            User provider = userRepository.findProviderById(providerId)
                    .orElseThrow(() -> new InternalException(ResponseMessage.NOT_EXIST_PROVIDER_ID));

            Optional<User> optionalUser = userRepository.findById(userId);
            if (optionalUser.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);
            }

            User user = optionalUser.get();

            Review review = Review.builder()
                    .user(user)
                    .provider(provider)
                    .reviewScore(score)
                    .reviewContent(content)
                    .reviewCreatedAt(LocalDateTime.now())
                    .build();

            reviewRepository.save(review);

            data = new CreateReviewResponseDto(review);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<List<GetReviewResponseDto>> getReviewsByProvider(Long providerId) {
        List<GetReviewResponseDto> data = null;
        try {
            Optional<List<Review>> reviews = reviewRepository.findReviewsByProvider(providerId);
            if (reviews.isPresent()) {
                data = reviews.get().stream()
                        .map(GetReviewResponseDto::new)
                        .collect(Collectors.toList());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<GetAvgReviewScoreResponseDto> getAverageReviewScoreByProvider(Long providerId) {
        GetAvgReviewScoreResponseDto data = null;
        try {
            Double avgScore = reviewRepository.findAvgReviewScoreByProvider(providerId)
                    .orElse(0.0);
            if (!userRepository.existsById(providerId)) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_PROVIDER_ID);
            }
            data = new GetAvgReviewScoreResponseDto(avgScore);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<GetReviewResponseDto> getByReviewId(Long reviewId) {
        GetReviewResponseDto data = null;
        try {
            Optional<Review> optionalReview = reviewRepository.findById(reviewId);
            if (optionalReview.isPresent()) {
                data = new GetReviewResponseDto(optionalReview.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<UpdateReviewResponseDto> updateReview(Long reviewId, Long reservationId ,UpdateReviewRequestDto dto) {
        UpdateReviewResponseDto data = null;
        int score = dto.getReviewScore();
        String content = dto.getReviewContent();

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new InternalException(ResponseMessage.NOT_EXIST_RESERVATION));

        if (reservation.getReservationStatus() != ReservationStatus.COMPLETED){
            return ResponseDto.setFailed(ResponseMessage.RESERVATION_IS_NOT_COMPLETED);
        }

        try {
            Optional<Review> optionalReview = reviewRepository.findById(reviewId);
            if (optionalReview.isPresent()) {
                Review respondedReview = optionalReview.get().toBuilder()
                        .reviewScore(score)
                        .reviewContent(content)
                        .build();
                reviewRepository.save(respondedReview);
                data = new UpdateReviewResponseDto(respondedReview);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<Void> deleteReview(Long reviewId) {
        try {
            if(!reviewRepository.existsById(reviewId)) ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
            reviewRepository.deleteById(reviewId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }
}