package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.heallthrordAttachment.response.HealthRecordAttachmentDTO;

import java.util.List;

public interface HealthRecordAttachmentService {

    // 특정 건강 기록의 첨부파일 조회 (DTO로 반환)
    ResponseDto<List<HealthRecordAttachmentDTO>> getAttachmentsByHealthRecordId(Long healthRecordId);

    // 특정 첨부파일 삭제
    void deleteAttachmentById(Long attachmentId);

    // 특정 건강 기록의 모든 첨부파일 삭제
    void deleteAttachmentsByHealthRecordId(Long healthRecordId);
}
