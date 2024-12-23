package com.korit.projectrrs.dto.review.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateReviewRequestDto {
    @NotBlank
    private Long reservationId;

    @Min(0)
    @Max(5)
    private int reviewScore;

    @NotBlank
    private String reviewContent;
}
