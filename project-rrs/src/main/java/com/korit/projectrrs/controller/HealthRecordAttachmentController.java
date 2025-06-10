package com.korit.projectrrs.controller;

import com.korit.projectrrs.common.constant.ApiMappingPattern;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.heallthrordAttachment.response.HealthRecordAttachmentDTO;
import com.korit.projectrrs.service.HealthRecordAttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.korit.projectrrs.common.constant.ApiMappingPattern.*;

@RestController
@RequestMapping(ApiMappingPattern.HEALTH_RECORDS_ATTACHMENT)
@RequiredArgsConstructor
public class HealthRecordAttachmentController {

    private final HealthRecordAttachmentService healthRecordAttachmentService;
    @GetMapping(HEALTH_RECORDS_ATTACHMENT_BY_RECORD_ID)
    public ResponseEntity<ResponseDto<List<HealthRecordAttachmentDTO>>> getAttachmentsByHealthRecordId(
            @PathVariable Long healthRecordId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {

        System.out.println("Authenticated User: " + userDetails.getUsername());

        ResponseDto<List<HealthRecordAttachmentDTO>> response = healthRecordAttachmentService.getAttachmentsByHealthRecordId(healthRecordId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(DELETE_ATTACHMENT_BY_ATTACHMENT_ID)
    public ResponseEntity<?> deleteAttachmentById(
            @PathVariable Long attachmentId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {

        System.out.println("Authenticated User: " + userDetails.getUsername());

        healthRecordAttachmentService.deleteAttachmentById(attachmentId);
        return ResponseEntity.ok("Attachment deleted successfully");
    }

    @DeleteMapping(DELETE_ATTACHMENT_BY_HEALTH_RECORD_ID)
    public ResponseEntity<?> deleteAttachmentsByHealthRecordId(
            @PathVariable Long healthRecordId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        System.out.println("Authenticated User: " + userDetails.getUsername());

        healthRecordAttachmentService.deleteAttachmentsByHealthRecordId(healthRecordId);
        return ResponseEntity.ok("All attachments for health record deleted successfully");
    }
}
