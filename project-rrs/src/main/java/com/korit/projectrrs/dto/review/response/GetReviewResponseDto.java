package com.korit.projectrrs.dto.review.response;

import com.korit.projectrrs.entity.Review;
import lombok.Data;

@Data
public class GetReviewResponseDto {
    private int reviewScore;
    private String reviewContent;

    public GetReviewResponseDto(Review review){
        this.reviewScore = review.getReviewScore();
        this.reviewContent = review.getReviewContent();
    }
}
