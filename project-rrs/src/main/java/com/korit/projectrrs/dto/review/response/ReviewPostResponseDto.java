package com.korit.projectrrs.dto.review.response;

import com.korit.projectrrs.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewPostResponseDto {
    private int reviewScore;
    private String reviewContent;
    private LocalDate reviewCreateAt;

    public ReviewPostResponseDto (Review review) {
        this.reviewScore = review.getReviewScore();
        this.reviewContent = review.getReviewContent();
        this.reviewCreateAt = review.getReviewCreateAt();
    }
}
