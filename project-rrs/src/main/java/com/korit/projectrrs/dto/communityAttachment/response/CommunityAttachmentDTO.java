package com.korit.projectrrs.dto.communityAttachment.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommunityAttachmentDTO {
    private Long attachmentId;
    private String communityAttachmentFile;
}
