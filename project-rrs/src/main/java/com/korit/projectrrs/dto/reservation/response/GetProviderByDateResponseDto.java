package com.korit.projectrrs.dto.reservation.response;

import com.korit.projectrrs.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
public class GetProviderByDateResponseDto {
    private Long providerId;
    private String profileImageUrl;
    private String providerNickname;
    private String providerUsername;
    private String providerIntroduction;
    private double avgReviewScore;

    public GetProviderByDateResponseDto(User provider, double avgReviewScore){
        this.providerId = provider.getUserId();
        this.profileImageUrl = provider.getProfileImageUrl();
        this.providerNickname = provider.getNickname();
        this.providerUsername = provider.getUsername();
        this.providerIntroduction = provider.getProviderIntroduction();
        this.avgReviewScore = avgReviewScore;
    }
}