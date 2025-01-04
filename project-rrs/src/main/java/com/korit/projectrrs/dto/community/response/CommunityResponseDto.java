package com.korit.projectrrs.dto.community.response;

import com.korit.projectrrs.dto.communitycomment.response.CommunityCommentResponseDto;
import com.korit.projectrrs.entity.Community;
import com.korit.projectrrs.entity.CommunityAttachment;
import com.korit.projectrrs.entity.CommunityLikes;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class CommunityResponseDto {
    private Long communityId;
    private String nickname;
    private String communityTitle;
    private LocalDateTime communityCreatedAt;
    private LocalDateTime communityUpdatedAt;
    private int communityLikeCount = 0;
    private String communityContent;
    private String communityThumbnailImageUrl;

    private List<CommunityCommentResponseDto> comments;
    private List<String> attachments;
    private List<Long> userLiked; // 사용자 ID 목록으로 수정

    /**
     * Community 엔티티를 기반으로 CommunityResponseDto를 생성합니다.
     *
     * @param community 변환할 Community 엔티티
     */
    public CommunityResponseDto(Community community) {
        this.communityId = community.getCommunityId();
        this.nickname = Optional.ofNullable(community.getUser())
                .map(user -> user.getNickname())
                .orElse("Unknown");
        this.communityTitle = community.getCommunityTitle();
        this.communityCreatedAt = community.getCommunityCreatedAt();
        this.communityUpdatedAt = community.getCommunityUpdatedAt();
        this.communityLikeCount = community.getCommunityLikeCount();
        this.communityContent = community.getCommunityContent();
        this.communityThumbnailImageUrl = Optional.ofNullable(community.getCommunityThumbnailUrl())
                .orElse("example.jpg");

        // userLikes: CommunityLikes 엔티티에서 userLiked가 true인 사용자 ID만 가져오기
        this.userLiked = Optional.ofNullable(community.getUserLiked())
                .orElse(Collections.emptyList())
                .stream()
                .filter(CommunityLikes::isUserLiked) // 좋아요가 활성화된 사용자만 필터링
                .map(like -> like.getUser().getUserId())
                .collect(Collectors.toList());

        // Comments: CommunityCommentResponseDto로 변환
        this.comments = Optional.ofNullable(community.getComments())
                .orElse(Collections.emptyList())
                .stream()
                .map(CommunityCommentResponseDto::new)
                .collect(Collectors.toList());

        // Attachments: 파일 경로만 리스트로 변환
        this.attachments = Optional.ofNullable(community.getAttachments())
                .orElse(Collections.emptyList())
                .stream()
                .map(CommunityAttachment::getCommunityAttachment) // 올바른 메서드 참조
                .collect(Collectors.toList());
    }
}
