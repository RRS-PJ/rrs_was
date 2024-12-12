package com.korit.projectrrs.dto.review.response;

import com.korit.projectrrs.entity.Review;

public class GetReviewResponseDto {
    private int reviewScore;
    private String reviewContent;

    public GetReviewResponseDto(Review review){
        this.reviewContent = review.getReviewContent();
        this.reviewScore = review.getReviewScore();
    }
}
