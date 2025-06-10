package com.korit.projectrrs.dto.community.response;

import com.korit.projectrrs.entity.Community;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

/**
 * 커뮤니티 게시글 리스트 응답 DTO
 */
@Getter
@Setter
public class CommunityResponseAllDto {

    private Long communityId;
    private String nickname;
    private String communityTitle;
    private int communityLikeCount;
    private LocalDateTime communityCreatedAt;
    private LocalDateTime communityUpdatedAt;
    private String communityContent;
    private String communityThumbnailFile;

    public CommunityResponseAllDto() {
        this.communityLikeCount = 0;
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
