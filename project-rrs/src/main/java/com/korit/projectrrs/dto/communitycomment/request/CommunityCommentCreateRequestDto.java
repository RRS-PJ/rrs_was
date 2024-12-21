package com.korit.projectrrs.dto.communitycomment.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommunityCommentCreateRequestDto {
    private Long communityId;
    @NotBlank(message = "Comment content cannot be empty")
    private String communityCommentContent;
}
