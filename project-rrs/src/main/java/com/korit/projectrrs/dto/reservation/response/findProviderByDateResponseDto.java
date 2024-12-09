package com.korit.projectrrs.dto.reservation.response;

import lombok.Data;

@Data
public class findProviderByDateResponseDto {
    private String profileImageUrl;
    private String providerNickname;
    private String providerUsername;
    private double avgReviewScore;
    private String providerIntroduction;
}
