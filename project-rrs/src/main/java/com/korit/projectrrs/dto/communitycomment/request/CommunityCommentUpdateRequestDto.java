package com.korit.projectrrs.dto.communitycomment.request;

import lombok.Data;

@Data
public class CommunityCommentUpdateRequestDto {
    private Long communityId;
    private Long commentId;
    private String communityCommentContent;
}
