package com.korit.projectrrs.dto.review.response;

import com.korit.projectrrs.entity.Review;
import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class ReviewPostResponseDto {
    private int reviewScore;
    private String reviewContent;

    public ReviewPostResponseDto(Review review){
        this.reviewContent = review.getReviewContent();
        this.reviewScore = review.getReviewScore();
    }
}
