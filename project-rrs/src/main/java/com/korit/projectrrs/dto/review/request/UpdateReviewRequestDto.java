package com.korit.projectrrs.dto.review.request;

import lombok.Data;

@Data
public class UpdateReviewRequestDto {
    private Long reviewId;
    private int reviewScore;
    private String reviewContent;
}
