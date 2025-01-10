package com.korit.projectrrs.controller;

import com.korit.projectrrs.common.ApiMappingPattern;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.heallthrordAttachment.response.HealthRecordAttachmentDTO;
import com.korit.projectrrs.service.HealthRecordAttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.HEALTH_RECORDS_ATTACHMENT)
@RequiredArgsConstructor
public class HealthRecordAttachmentController {

    private final HealthRecordAttachmentService healthRecordAttachmentService;

    // 특정 건강 기록의 첨부파일 조회
    @GetMapping("/health-record/{healthRecordId}")
    public ResponseEntity<ResponseDto<List<HealthRecordAttachmentDTO>>> getAttachmentsByHealthRecordId(
            @PathVariable Long healthRecordId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        // 인증된 사용자 확인
        System.out.println("Authenticated User: " + userDetails.getUsername());

        // Service 호출
        ResponseDto<List<HealthRecordAttachmentDTO>> response = healthRecordAttachmentService.getAttachmentsByHealthRecordId(healthRecordId);
        return ResponseEntity.ok(response);
    }

    // 특정 첨부파일 삭제
    @DeleteMapping("/{attachmentId}")
    public ResponseEntity<?> deleteAttachmentById(
            @PathVariable Long attachmentId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        // 인증된 사용자 확인
        System.out.println("Authenticated User: " + userDetails.getUsername());

        // 권한 체크 로직 추가 가능
        healthRecordAttachmentService.deleteAttachmentById(attachmentId);
        return ResponseEntity.ok("Attachment deleted successfully");
    }

    // 특정 건강 기록의 모든 첨부파일 삭제
    @DeleteMapping("/health-record/{healthRecordId}")
    public ResponseEntity<?> deleteAttachmentsByHealthRecordId(
            @PathVariable Long healthRecordId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        // 인증된 사용자 확인
        System.out.println("Authenticated User: " + userDetails.getUsername());

        // 권한 체크 로직 추가 가능
        healthRecordAttachmentService.deleteAttachmentsByHealthRecordId(healthRecordId);
        return ResponseEntity.ok("All attachments for health record deleted successfully");
    }
}
