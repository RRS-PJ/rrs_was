package com.korit.projectrrs.dto.reservation.response;

import com.korit.projectrrs.entity.User;
import lombok.Data;

@Data
public class GetChosenProviderInfoResponseDto {
    private Long providerId;
    private String profileImageUrl;
    private String providerNickname;
    private String providerUsername;
    private String providerIntroduction;
    private double avgReviewScore;

    public GetChosenProviderInfoResponseDto(User provider, double avgReviewScore){
        this.providerId = provider.getUserId();
        this.profileImageUrl = provider.getProfileImageUrl();
        this.providerNickname = provider.getNickname();
        this.providerUsername = provider.getUsername();
        this.providerIntroduction = provider.getProviderIntroduction();
        this.avgReviewScore = avgReviewScore;
    }
}
