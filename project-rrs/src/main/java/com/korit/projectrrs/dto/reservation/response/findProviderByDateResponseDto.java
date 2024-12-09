package com.korit.projectrrs.dto.reservation.response;

import lombok.Data;

@Data
public class findProviderByDateResponseDto {
    private Long providerId;
    private String profileImageUrl;
    private String providerNickname;
    private String providerUsername;
    private String providerIntroduction;
    private double avgReviewScore;
}