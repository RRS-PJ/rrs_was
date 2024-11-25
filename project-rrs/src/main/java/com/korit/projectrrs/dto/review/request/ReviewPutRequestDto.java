package com.korit.projectrrs.dto.review.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewPutRequestDto {
    private Long providerId;
    private int reviewScore;
    private String reviewContent;
}
