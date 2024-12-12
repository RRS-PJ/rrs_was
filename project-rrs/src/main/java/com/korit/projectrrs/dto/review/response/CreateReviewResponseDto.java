package com.korit.projectrrs.dto.review.response;

import com.korit.projectrrs.entity.Review;

public class CreateReviewResponseDto {
    private int reviewScore;
    private String reviewContent;

    public CreateReviewResponseDto(Review review){
        this.reviewContent = review.getReviewContent();
        this.reviewScore = review.getReviewScore();
    }
}
