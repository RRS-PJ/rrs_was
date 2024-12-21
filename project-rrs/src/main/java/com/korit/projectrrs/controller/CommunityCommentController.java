package com.korit.projectrrs.controller;

import com.korit.projectrrs.common.ApiMappingPattern;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.communitycomment.request.CommunityCommentCreateRequestDto;
import com.korit.projectrrs.dto.communitycomment.request.CommunityCommentUpdateRequestDto;
import com.korit.projectrrs.dto.communitycomment.response.CommunityCommentResponseDto;
import com.korit.projectrrs.security.PrincipalUser;
import com.korit.projectrrs.service.CommunityCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.COMMENT)
@RequiredArgsConstructor
public class CommunityCommentController {

    private final CommunityCommentService communityCommentService;

    @PostMapping
    public ResponseEntity<ResponseDto<CommunityCommentResponseDto>> createComment(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestBody @Valid CommunityCommentCreateRequestDto dto
    ) {
        if (principalUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<CommunityCommentResponseDto> response = communityCommentService.createComment(userId, dto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<ResponseDto<CommunityCommentResponseDto>> updateComment(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long commentId,
            @RequestBody @Valid CommunityCommentUpdateRequestDto dto
    ) {
        if (principalUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long userId = principalUser.getUser().getUserId();
        dto.setCommentId(commentId); // Ensure DTO has the correct commentId
        ResponseDto<CommunityCommentResponseDto> response = communityCommentService.updateComment(userId, dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-community/{communityId}")
    public ResponseEntity<List<CommunityCommentResponseDto>> getCommentsByCommunity(
            @PathVariable Long communityId
    ) {
        List<CommunityCommentResponseDto> comments = communityCommentService.getCommentsByCommunity(communityId);
        return ResponseEntity.ok(comments);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long commentId
    ) {
        if (principalUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long userId = principalUser.getUser().getUserId();
        communityCommentService.deleteComment(userId, commentId);
        return ResponseEntity.noContent().build();
    }
}
