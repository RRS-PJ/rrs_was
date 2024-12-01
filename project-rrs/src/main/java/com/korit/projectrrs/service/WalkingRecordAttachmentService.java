package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.walkingRecordAttachment.responseDto.WalkingRecordAttachmentResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface WalkingRecordAttachmentService {
    ResponseDto<WalkingRecordAttachmentResponseDto> createWalkingRecordAttachment(String userId, long walkingRecordId, List<MultipartFile> file);
    ResponseDto<Void> deleteWalkingRecord(String userId, long walkingRecordId);
}
