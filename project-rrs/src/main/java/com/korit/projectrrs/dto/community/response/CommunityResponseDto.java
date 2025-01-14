package com.korit.projectrrs.dto.community.response;

import com.korit.projectrrs.dto.communitycomment.response.CommunityCommentResponseDto;
import com.korit.projectrrs.entity.Community;
import com.korit.projectrrs.entity.CommunityAttachment;
import com.korit.projectrrs.entity.CommunityLikes;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 커뮤니티 게시글 상세 응답 DTO
 */
@Getter
@Setter
public class CommunityResponseDto {

    private Long communityId;
    private String nickname;
    private String communityTitle;
    private LocalDateTime communityCreatedAt;
    private LocalDateTime communityUpdatedAt;
    private int communityLikeCount = 0;
    private String communityContent;
    private String communityThumbnailFile;

    private List<CommunityCommentResponseDto> comments = new ArrayList<>();
    private List<String> attachments = new ArrayList<>();
    private List<String> userLikedNicknames = new ArrayList<>();
    /**
     * Community 엔티티를 기반으로 CommunityResponseDto를 생성합니다.
     * @param community Community 엔티티
     */
    public CommunityResponseDto(Community community) {
        this.communityId = community.getCommunityId();
        this.nickname = community.getUser() != null ? community.getUser().getNickname() : "Unknown";
        this.communityTitle = community.getCommunityTitle();
        this.communityCreatedAt = community.getCommunityCreatedAt();
        this.communityUpdatedAt = community.getCommunityUpdatedAt();
        this.communityLikeCount = community.getCommunityLikeCount();
        this.communityContent = community.getCommunityContent();
        this.communityThumbnailFile = community.getCommunityThumbnailFile();

        // 좋아요한 사용자 닉네임 목록
        if (Objects.nonNull(community.getUserLiked())) {
            this.userLikedNicknames = community.getUserLiked().stream()
                    .filter(CommunityLikes::isUserLiked)
                    .map(like -> like.getUser().getNickname())
                    .collect(Collectors.toList());
        }

        // 댓글 리스트 변환
        if (Objects.nonNull(community.getComments())) {
            this.comments = community.getComments().stream()
                    .map(CommunityCommentResponseDto::new)
                    .collect(Collectors.toList());
        }

        // 첨부파일 리스트 변환
        if (Objects.nonNull(community.getAttachments())) {
            this.attachments = community.getAttachments().stream()
                    .map(CommunityAttachment::getCommunityAttachment)
                    .collect(Collectors.toList());
        }
    }
}
