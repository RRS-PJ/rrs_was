package com.korit.projectrrs.dto.review.request;

import lombok.Data;

@Data
public class ReviewPutRequestDto {
    private Long reviewId;
    private int reviewScore;
    private String reviewContent;
}
