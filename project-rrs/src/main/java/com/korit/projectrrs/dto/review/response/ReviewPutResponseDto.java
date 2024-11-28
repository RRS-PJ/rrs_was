package com.korit.projectrrs.dto.review.response;

import com.korit.projectrrs.entity.Review;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewPutResponseDto {
    private Long reviewId;
    private LocalDateTime reviewCreateAt;
    private int reviewScore;
    private String reviewContent;

    public ReviewPutResponseDto (Review review) {
        this.reviewCreateAt = review.getReviewCreateAt();
        this.reviewScore = review.getReviewScore();
        this.reviewContent = review.getReviewContent();
    }
}
