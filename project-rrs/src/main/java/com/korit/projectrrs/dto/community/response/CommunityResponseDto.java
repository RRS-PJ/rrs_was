package com.korit.projectrrs.dto.community.response;

import com.korit.projectrrs.dto.communitycomment.response.CommunityCommentResponseDto;
import com.korit.projectrrs.entity.Community;
import com.korit.projectrrs.entity.CommunityAttachment;
import com.korit.projectrrs.entity.CommunityLikes;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 커뮤니티 게시글 상세 응답 DTO
 */
@Getter
@Setter
public class CommunityResponseDto {

    private Long communityId;                // 게시글 ID
    private String nickname;                 // 작성자 닉네임
    private String communityTitle;           // 게시글 제목
    private LocalDateTime communityCreatedAt; // 생성 날짜
    private LocalDateTime communityUpdatedAt; // 수정 날짜
    private int communityLikeCount = 0;      // 좋아요 수 기본값: 0
    private String communityContent;         // 게시글 내용
    private String communityThumbnailFile; // 썸네일 이미지 URL

    private List<CommunityCommentResponseDto> comments = Collections.emptyList(); // 댓글
    private List<String> attachments = Collections.emptyList(); // 첨부파일 경로
    private List<String> userLikedNicknames = Collections.emptyList(); // 좋아요한 사용자 ID 목록

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
        this.communityThumbnailFile = community.getCommunityThumbnailFile() != null
                ? community.getCommunityThumbnailFile()
                : "default-thumbnail.jpg";

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
