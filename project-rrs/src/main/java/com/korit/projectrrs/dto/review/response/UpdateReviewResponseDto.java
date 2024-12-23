package com.korit.projectrrs.dto.review.response;

import com.korit.projectrrs.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UpdateReviewResponseDto {
    private int reviewScore;
    private String reviewContent;

    public UpdateReviewResponseDto(Review review){
        this.reviewScore = review.getReviewScore();
        this.reviewContent = review.getReviewContent();
    }
}
