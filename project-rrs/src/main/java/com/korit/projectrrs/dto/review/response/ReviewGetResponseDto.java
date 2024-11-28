package com.korit.projectrrs.dto.review.response;

import com.korit.projectrrs.entity.Review;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewGetResponseDto {
    private LocalDateTime reviewCreateAt;
    private int reviewScore;
    private String reviewContent;

    public ReviewGetResponseDto (Review review) {
        this.reviewCreateAt = review.getReviewCreateAt();
        this.reviewScore = review.getReviewScore();
        this.reviewContent = review.getReviewContent();
    }
}
