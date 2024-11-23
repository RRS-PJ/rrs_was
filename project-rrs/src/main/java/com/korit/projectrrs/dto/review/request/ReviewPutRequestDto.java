package com.korit.projectrrs.dto.review.request;

import lombok.Data;

@Data
public class ReviewPutRequestDto {
    private int reviewScore;
    private String reviewContent;
}
