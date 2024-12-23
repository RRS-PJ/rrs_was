package com.korit.projectrrs.dto.community.response;

import com.korit.projectrrs.entity.Community;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CommunityResponseAllDto {
    private Long communityId;
    private String nickname;
    private String communityTitle;
    private int communityLikeCount = 0;
    private LocalDateTime communityCreatedAt;
    private LocalDateTime communityUpdatedAt;
    private String communityContent;
    private String communityThumbnailUrl; // Thumbnail URL 타입 수정

    public CommunityResponseAllDto(Community community) {
        this.communityId = community.getCommunityId();
        this.nickname = community.getUser().getNickname(); // 작성자의 닉네임 설정
        this.communityTitle = community.getCommunityTitle();
        this.communityLikeCount = community.getCommunityLikeCount();
        this.communityCreatedAt = community.getCommunityCreatedAt();
        this.communityUpdatedAt = community.getCommunityUpdatedAt();
        this.communityContent = community.getCommunityContent();
        this.communityThumbnailUrl = community.getCommunityThumbnailUrl(); // URL 값 가져오기
    }
}
