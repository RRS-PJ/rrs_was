package com.korit.projectrrs.dto.community.response;

import com.korit.projectrrs.dto.communitycomment.response.CommunityCommentResponseDto;
import com.korit.projectrrs.entity.Community;
import com.korit.projectrrs.entity.CommunityAttachment;
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
    private String communityContent;
    private String communityThumbnailImageUrl; // 썸네일 URL을 String으로 변경

    private List<CommunityCommentResponseDto> comments;

    private List<String> attachments;

    public CommunityResponseDto(Community community) {
        this.communityId = community.getCommunityId();
        this.nickname = community.getUser().getNickname(); // User 객체에서 닉네임 가져오기
        this.communityTitle = community.getCommunityTitle();
        this.communityCreatedAt = community.getCommunityCreatedAt();
        this.communityUpdatedAt = community.getCommunityUpdatedAt();
        this.communityContent = community.getCommunityContent();
        this.communityThumbnailImageUrl = community.getCommunityThumbnailUrl(); // String 값 설정

        // Comments
        if (community.getComments() != null && !community.getComments().isEmpty()) {
            this.comments = community.getComments().stream()
                    .map(CommunityCommentResponseDto::new)
                    .collect(Collectors.toList());
        } else {
            this.comments = null;
        }

        // Attachments
        this.attachments = Optional.ofNullable(community.getAttachments())
                .orElseGet(Collections::emptyList) // null일 경우 빈 리스트 반환
                .stream()
                .map(CommunityAttachment::getCommunityAttachment) // 파일 경로를 리스트로 변환
                .collect(Collectors.toList());
    }
}
