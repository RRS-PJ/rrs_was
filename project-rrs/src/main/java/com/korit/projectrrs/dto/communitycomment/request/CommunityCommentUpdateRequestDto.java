package com.korit.projectrrs.dto.communitycomment.request;

import lombok.Data;

@Data
public class CommunityCommentUpdateRequestDto {
    private Long communityId;
    private Long commentId; // 수정할 댓글 ID
    private String communityCommentContent; // 새 댓글 내용
}
