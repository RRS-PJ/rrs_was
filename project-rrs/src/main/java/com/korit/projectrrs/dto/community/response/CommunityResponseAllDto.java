package com.korit.projectrrs.dto.community.response;

import com.korit.projectrrs.entity.Community;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 커뮤니티 게시글 리스트 응답 DTO
 */
@Getter
@Setter
public class CommunityResponseAllDto {

    private Long communityId;                // 게시글 ID
    private String nickname;                 // 작성자 닉네임
    private String communityTitle;           // 게시글 제목
    private int communityLikeCount;          // 좋아요 수
    private LocalDateTime communityCreatedAt; // 생성 날짜
    private LocalDateTime communityUpdatedAt; // 수정 날짜
    private String communityContent;         // 게시글 내용
    private String communityThumbnailFile;    // 썸네일 URL

    public CommunityResponseAllDto() {
        this.communityLikeCount = 0; // 기본값 설정
    }

    public CommunityResponseAllDto(Community community) {
        this.communityId = community.getCommunityId();
        this.nickname = community.getUser().getNickname();
        this.communityTitle = community.getCommunityTitle();
        this.communityLikeCount = community.getCommunityLikeCount();
        this.communityCreatedAt = community.getCommunityCreatedAt();
        this.communityUpdatedAt = community.getCommunityUpdatedAt();
        this.communityContent = community.getCommunityContent();
        this.communityThumbnailFile = community.getCommunityThumbnailFile();
    }
}
