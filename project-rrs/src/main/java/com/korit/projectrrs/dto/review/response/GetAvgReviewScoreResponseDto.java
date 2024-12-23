package com.korit.projectrrs.dto.review.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetAvgReviewScoreResponseDto {
    private Double avgScore;
}
