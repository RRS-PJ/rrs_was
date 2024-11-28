package com.korit.projectrrs.dto.review.response;

import com.korit.projectrrs.entity.Review;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewPostResponseDto {
    private int reviewScore;
    private String reviewContent;
    private LocalDateTime reviewCreateAt;

    public ReviewPostResponseDto (Review review) {
        this.reviewScore = review.getReviewScore();
        this.reviewContent = review.getReviewContent();
        this.reviewCreateAt = review.getReviewCreateAt();
    }
}
