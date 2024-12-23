package com.korit.projectrrs.dto.communitycomment.response;

import com.korit.projectrrs.entity.CommunityComment;
import lombok.Data;

@Data
public class CommunityCommentResponseDto {
    private String nickname;
    private String communityContent;

    public CommunityCommentResponseDto(CommunityComment comment) {
        this.nickname = comment.getNickname();
        this.communityContent = comment.getCommunityCommentContent();
    }
}
