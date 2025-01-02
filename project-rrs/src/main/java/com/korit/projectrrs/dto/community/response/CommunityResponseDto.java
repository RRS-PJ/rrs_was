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

    public CommunityResponseDto(Community community) {
        this.communityId = community.getCommunityId();
        this.nickname = community.getUser().getNickname(); // User 객체에서 닉네임 가져오기
        this.communityTitle = community.getCommunityTitle();
        this.communityCreatedAt = community.getCommunityCreatedAt();
        this.communityUpdatedAt = community.getCommunityUpdatedAt();
        this.communityLikeCount = community.getCommunityLikeCount();
        this.communityContent = community.getCommunityContent();
        this.communityThumbnailImageUrl = community.getCommunityThumbnailUrl(); // String 값 설정

        // userLiked: CommunityLikes 엔티티에서 사용자 ID만 가져오기
        this.userLiked = Optional.ofNullable(community.getUserLiked())
                .orElse(Collections.emptyList())
                .stream()
                .filter(CommunityLikes::isUserLiked) // 좋아요가 활성화된 사용자만 가져옴
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
                .map(CommunityAttachment::getCommunityAttachment)
                .collect(Collectors.toList());
    }
}
