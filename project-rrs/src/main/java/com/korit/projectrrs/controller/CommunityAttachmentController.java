package com.korit.projectrrs.controller;

import com.korit.projectrrs.common.constant.ApiMappingPattern;
import com.korit.projectrrs.dto.communityAttachment.response.CommunityAttachmentDTO;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.service.CommunityAttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.ATTACHMENT)
@RequiredArgsConstructor
public class CommunityAttachmentController {

    private final CommunityAttachmentService communityAttachmentService;

    // 특정 커뮤니티의 첨부파일 조회
    @GetMapping("/community/{communityId}")
    public ResponseEntity<ResponseDto<List<CommunityAttachmentDTO>>> getAttachmentsByCommunityId(
            @PathVariable Long communityId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        // 인증된 사용자 확인
        System.out.println("Authenticated User: " + userDetails.getUsername());

        // Service 호출
        ResponseDto<List<CommunityAttachmentDTO>> response = communityAttachmentService.getAttachmentsByCommunityId(communityId);
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
        communityAttachmentService.deleteAttachmentById(attachmentId);
        return ResponseEntity.ok("Attachment deleted successfully");
    }

    // 특정 커뮤니티의 모든 첨부파일 삭제
    @DeleteMapping("/community/{communityId}")
    public ResponseEntity<?> deleteAttachmentsByCommunityId(
            @PathVariable Long communityId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        // 인증된 사용자 확인
        System.out.println("Authenticated User: " + userDetails.getUsername());

        // 권한 체크 로직 추가 가능
        communityAttachmentService.deleteAttachmentsByCommunityId(communityId);
        return ResponseEntity.ok("All attachments for community deleted successfully");
    }
}
