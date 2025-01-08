package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;

public interface WalkingRecordAttachmentService {
    ResponseDto<Void> deleteWalkingRecordAttachment(Long userId, Long attachmentId);
}
