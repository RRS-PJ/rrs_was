package com.korit.projectrrs.dto.walkingRecordAttachment.responseDto;

import com.korit.projectrrs.entity.WalkingRecordAttachment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.file.Paths;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalkingRecordAttachmentResponseDto {
    private String fileName;
    private String fileUrl;

    public WalkingRecordAttachmentResponseDto(WalkingRecordAttachment attachment) {
        this.fileName = Paths.get(attachment.getWalkingRecordAttachmentFile()).getFileName().toString();
        this.fileUrl = attachment.getWalkingRecordAttachmentFile();
    }
}
