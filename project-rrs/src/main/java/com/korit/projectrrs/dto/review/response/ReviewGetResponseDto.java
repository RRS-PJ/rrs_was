package com.korit.projectrrs.dto.review.response;

import com.korit.projectrrs.entity.Review;

public class ReviewGetResponseDto {
    private int reviewScore;
    private String reviewContent;

    public ReviewGetResponseDto(Review review){
        this.reviewContent = review.getReviewContent();
        this.reviewScore = review.getReviewScore();
    }
}
