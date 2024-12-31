package com.korit.projectrrs.dto.review.response;

import com.korit.projectrrs.entity.Review;
import lombok.Data;

@Data
public class CreateReviewResponseDto {
    private Double reviewScore;
    private String reviewContent;

    public CreateReviewResponseDto(Review review){
        this.reviewContent = review.getReviewContent();
        this.reviewScore = review.getReviewScore();
    }
}
