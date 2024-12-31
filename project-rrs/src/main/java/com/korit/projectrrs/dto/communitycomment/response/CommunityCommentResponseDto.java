package com.korit.projectrrs.dto.communitycomment.response;

import com.korit.projectrrs.entity.CommunityComment;
import com.korit.projectrrs.entity.User;
import lombok.Data;

@Data
public class CommunityCommentResponseDto {
    private Long commentId;
    private String nickname;
    private String communityContent;

    public CommunityCommentResponseDto(CommunityComment comment) {
        this.commentId = comment.getCommentId();
        this.nickname = comment.getNickname();
        this.communityContent = comment.getCommunityCommentContent();
    }
}
