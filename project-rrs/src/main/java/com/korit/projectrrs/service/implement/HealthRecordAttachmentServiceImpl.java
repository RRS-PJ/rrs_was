package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.heallthrordAttachment.response.HealthRecordAttachmentDTO;
import com.korit.projectrrs.entity.HealthRecordAttachment;
import com.korit.projectrrs.repositoiry.HealthRecordAttachmentRepository;
import com.korit.projectrrs.service.FileService;
import com.korit.projectrrs.service.HealthRecordAttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HealthRecordAttachmentServiceImpl implements HealthRecordAttachmentService {

    private final HealthRecordAttachmentRepository healthRecordAttachmentRepository;
    private final FileService fileService;

    @Override
    public ResponseDto<List<HealthRecordAttachmentDTO>> getAttachmentsByHealthRecordId(Long healthRecordId) {

        // HealthRecord ID로 첨부파일 조회
        List<HealthRecordAttachment> attachments = healthRecordAttachmentRepository.findByHealthRecordId(healthRecordId);

        // DTO로 변환
        List<HealthRecordAttachmentDTO> attachmentDTOs = attachments.stream()
                .map(attachment -> new HealthRecordAttachmentDTO(
                        attachment.getAttachmentId(), // 엔티티 필드 이름에 맞게 수정
                        attachment.getHealthRecordAttachmentFile() // 엔티티 필드 이름에 맞게 수정
                ))
                .collect(Collectors.toList());

        return ResponseDto.setSuccess("Attachments fetched successfully", attachmentDTOs);
    }

    @Override
    public void deleteAttachmentById(Long attachmentId) {

        // 첨부파일 조회
        HealthRecordAttachment attachment = healthRecordAttachmentRepository.findById(attachmentId)
                .orElseThrow(() -> new RuntimeException("Attachment not found"));

        // 첨부파일 삭제
        if (attachment.getHealthRecordAttachmentFile() != null) { // 필드 이름에 맞게 수정
            fileService.removeFile(attachment.getHealthRecordAttachmentFile());
        }

        healthRecordAttachmentRepository.deleteById(attachmentId);
    }

    @Override
    public void deleteAttachmentsByHealthRecordId(Long healthRecordId) {

        // HealthRecord ID로 첨부파일 조회
        List<HealthRecordAttachment> attachments = healthRecordAttachmentRepository.findByHealthRecordId(healthRecordId);

        // 첨부파일 삭제
        for (HealthRecordAttachment attachment : attachments) {
            if (attachment.getHealthRecordAttachmentFile() != null) { // 필드 이름에 맞게 수정
                fileService.removeFile(attachment.getHealthRecordAttachmentFile());
            }
        }

        healthRecordAttachmentRepository.deleteAll(attachments);
    }
}
