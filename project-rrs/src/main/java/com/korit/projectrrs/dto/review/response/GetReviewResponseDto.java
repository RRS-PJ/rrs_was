package com.korit.projectrrs.dto.review.response;

import com.korit.projectrrs.entity.Review;
import com.korit.projectrrs.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class GetReviewResponseDto {
    private Long reviewId;
    private Double reviewScore;
    private String reviewContent;
    private String userNickname;
    private String username;
    private String profileImageUrl;
    private LocalDateTime reviewCreatedAt;

    public GetReviewResponseDto(Review review){
        this.reviewId = review.getReviewId();
        this.reviewScore = review.getReviewScore();
        this.reviewContent = review.getReviewContent();
        this.reviewCreatedAt = review.getReviewCreatedAt();
    }
}
