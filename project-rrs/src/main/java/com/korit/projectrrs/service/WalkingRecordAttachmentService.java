package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.walkingRecordAttachment.response.WalkingRecordAttachmentResponseDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface WalkingRecordAttachmentService {
    ResponseDto<List<WalkingRecordAttachmentResponseDto>> createWalkingRecordAttachment(Long userId, Long petId, Long walkingRecordId, List<MultipartFile> file);
    ResponseDto<Void> deleteWalkingRecord(Long userId, Long petProfileId, Long walkingRecordId);
}
