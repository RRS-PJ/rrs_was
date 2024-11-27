package com.korit.projectrrs.dto.review.response;

import com.korit.projectrrs.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
