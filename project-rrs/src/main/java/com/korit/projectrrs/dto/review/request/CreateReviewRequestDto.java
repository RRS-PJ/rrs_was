package com.korit.projectrrs.dto.review.request;

import lombok.Data;

@Data
public class CreateReviewRequestDto {
    private Long providerId;
    private int reviewScore;
    private String reviewContent;
}
