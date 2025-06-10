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

        List<HealthRecordAttachment> attachments = healthRecordAttachmentRepository.findByHealthRecordId(healthRecordId);

        List<HealthRecordAttachmentDTO> attachmentDTOs = attachments.stream()
                .map(attachment -> new HealthRecordAttachmentDTO(
                        attachment.getAttachmentId(),
                        attachment.getHealthRecordAttachmentFile()
                ))
                .collect(Collectors.toList());

        return ResponseDto.setSuccess("Attachments fetched successfully", attachmentDTOs);
    }

    @Override
    public void deleteAttachmentById(Long attachmentId) {

        HealthRecordAttachment attachment = healthRecordAttachmentRepository.findById(attachmentId)
                .orElseThrow(() -> new RuntimeException("Attachment not found"));

        if (attachment.getHealthRecordAttachmentFile() != null) {
            fileService.removeFile(attachment.getHealthRecordAttachmentFile());
        }

        healthRecordAttachmentRepository.deleteById(attachmentId);
    }

    @Override
    public void deleteAttachmentsByHealthRecordId(Long healthRecordId) {

        List<HealthRecordAttachment> attachments = healthRecordAttachmentRepository.findByHealthRecordId(healthRecordId);

        for (HealthRecordAttachment attachment : attachments) {
            if (attachment.getHealthRecordAttachmentFile() != null) {
                fileService.removeFile(attachment.getHealthRecordAttachmentFile());
            }
        }
        healthRecordAttachmentRepository.deleteAll(attachments);
    }
}
