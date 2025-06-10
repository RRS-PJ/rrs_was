package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.heallthrordAttachment.response.HealthRecordAttachmentDTO;

import java.util.List;

public interface HealthRecordAttachmentService {
    ResponseDto<List<HealthRecordAttachmentDTO>> getAttachmentsByHealthRecordId(Long healthRecordId);
    void deleteAttachmentById(Long attachmentId);
    void deleteAttachmentsByHealthRecordId(Long healthRecordId);
}
