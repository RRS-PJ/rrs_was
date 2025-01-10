package com.korit.projectrrs.dto.heallthrordAttachment.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HealthRecordAttachmentDTO {
    private Long attachmentId;
    private String healthRecordAttachmentFile;
}
