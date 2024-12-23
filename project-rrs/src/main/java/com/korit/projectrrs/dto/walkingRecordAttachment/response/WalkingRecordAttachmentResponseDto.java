package com.korit.projectrrs.dto.walkingRecordAttachment.response;

import com.korit.projectrrs.entity.WalkingRecordAttachment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WalkingRecordAttachmentResponseDto {
    private String fileName;
    private String fileUrl;

    public WalkingRecordAttachmentResponseDto(WalkingRecordAttachment attachment) {
        this.fileName = Paths.get(attachment.getWalkingRecordAttachmentFile()).getFileName().toString();
        this.fileUrl = attachment.getWalkingRecordAttachmentFile();
    }
}
