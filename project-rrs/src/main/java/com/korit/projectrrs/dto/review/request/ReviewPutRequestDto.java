package com.korit.projectrrs.dto.review.request;

import lombok.Data;

@Data
public class ReviewPutRequestDto {
    private Long reviewId;
    private Long providerId;
    private int reviewScore;
    private String reviewContent;
}
