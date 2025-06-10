package com.korit.projectrrs.dto.provider.response;

import com.korit.projectrrs.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOneProviderInfoResponseDto {
    private Long providerId;
    private String profileImageUrl;
    private String providerUsername;
    private String providerNickname;
    private String providerIntroduction;
    private double avgReviewScore;

    public GetOneProviderInfoResponseDto(User provider, double avgReviewScore){
        this.providerId = provider.getUserId();
        this.profileImageUrl = provider.getProfileImageUrl();
        this.providerUsername = provider.getUsername();
        this.providerNickname = provider.getNickname();
        this.providerIntroduction = provider.getProviderIntroduction();
        this.avgReviewScore = avgReviewScore;
    }
}