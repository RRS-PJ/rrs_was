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

import static com.korit.projectrrs.common.constant.ApiMappingPattern.*;

@RestController
@RequestMapping(ApiMappingPattern.COMMUNITY_ATTACHMENT)
@RequiredArgsConstructor
public class CommunityAttachmentController {

    private final CommunityAttachmentService communityAttachmentService;

    @GetMapping(ATTACHMENT_BY_COMMUNITY_ID)
    public ResponseEntity<ResponseDto<List<CommunityAttachmentDTO>>> getAttachmentsByCommunityId(
            @PathVariable Long communityId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        System.out.println("Authenticated User: " + userDetails.getUsername());

        ResponseDto<List<CommunityAttachmentDTO>> response = communityAttachmentService.getAttachmentsByCommunityId(communityId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(ATTACHMENT_BY_ATTACHMENT_ID)
    public ResponseEntity<?> deleteAttachmentById(
            @PathVariable Long attachmentId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {

        System.out.println("Authenticated User: " + userDetails.getUsername());

        communityAttachmentService.deleteAttachmentById(attachmentId);
        return ResponseEntity.ok("Attachment deleted successfully");
    }

    @DeleteMapping(DELETE_ATTACHMENT_BY_COMMUNITY_ID)
    public ResponseEntity<?> deleteAttachmentsByCommunityId(
            @PathVariable Long communityId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {

        System.out.println("Authenticated User: " + userDetails.getUsername());

        communityAttachmentService.deleteAttachmentsByCommunityId(communityId);
        return ResponseEntity.ok("All attachments for community deleted successfully");
    }
}
