package com.korit.projectrrs.dto.communitycomment.response;

import com.korit.projectrrs.entity.CommunityComment;
import lombok.Data;

@Data
public class CommunityCommentResponseDto {
    private Long userId;
    private Long commentId;
    private String nickname;
    private String communityContent;

    public CommunityCommentResponseDto(CommunityComment comment) {
        this.userId = comment.getUserId();
        this.commentId = comment.getCommunityCommentId();
        this.nickname = comment.getNickname();
        this.communityContent = comment.getCommunityCommentContent();
    }
}
