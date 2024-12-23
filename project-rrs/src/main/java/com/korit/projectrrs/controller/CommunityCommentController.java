package com.korit.projectrrs.controller;

import com.korit.projectrrs.common.ApiMappingPattern;
import com.korit.projectrrs.common.ResponseMessage;
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

import static com.korit.projectrrs.common.ApiMappingPattern.*;

@RestController
@RequestMapping(ApiMappingPattern.COMMENT)
@RequiredArgsConstructor
public class CommunityCommentController {

    private final CommunityCommentService communityCommentService;

    @PostMapping(COMMENT_CREATE)
    public ResponseEntity<ResponseDto<CommunityCommentResponseDto>> createComment(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long communityId,
            @RequestBody @Valid CommunityCommentCreateRequestDto dto
    ) {
        if (principalUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ResponseDto.setFailed(ResponseMessage.USER_NOT_AUTHENTICATED));
        }
        Long userId = principalUser.getUser().getUserId();
        dto.setCommunityId(communityId);
        ResponseDto<CommunityCommentResponseDto> response = communityCommentService.createComment(userId, dto);
        if (response.getMessage().equals(ResponseMessage.NOT_AUTHORIZED_TO_CREATE_COMMENT)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
        return ResponseEntity.status(response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(response);
    }

    @PutMapping(COMMENT_PUT)
    public ResponseEntity<ResponseDto<CommunityCommentResponseDto>> updateComment(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long communityId,
            @PathVariable Long commentId,
            @RequestBody @Valid CommunityCommentUpdateRequestDto dto
    ) {
        if (principalUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ResponseDto.setFailed(ResponseMessage.USER_NOT_AUTHENTICATED));
        }
        Long userId = principalUser.getUser().getUserId();
        dto.setCommentId(commentId);
        dto.setCommunityId(communityId);
        ResponseDto<CommunityCommentResponseDto> response = communityCommentService.updateComment(userId, dto);
        if (response.getMessage().equals(ResponseMessage.NOT_AUTHORIZED_TO_UPDATE_COMMENT)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
        return ResponseEntity.status(response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(response);
    }

    @GetMapping(COMMENT_GET_BY_COMMUNITY_ID)
    public ResponseEntity<ResponseDto<List<CommunityCommentResponseDto>>> getCommentsByCommunity(
            @PathVariable Long communityId
    ) {
        ResponseDto<List<CommunityCommentResponseDto>> response = communityCommentService.getCommentsByCommunity(communityId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NO_CONTENT;
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping(COMMENT_DELETE_BY_COMMUNITY_ID)
    public ResponseEntity<ResponseDto<Void>> deleteComment(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long communityId,
            @PathVariable Long commentId
    ) {
        if (principalUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ResponseDto.setFailed(ResponseMessage.USER_NOT_AUTHENTICATED));
        }
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<Void> response = communityCommentService.deleteCommentFromCommunity(userId, communityId, commentId);
        if (response.getMessage().equals(ResponseMessage.NOT_AUTHORIZED_TO_DELETE_COMMENT)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
        HttpStatus status = response.isResult() ? HttpStatus.NO_CONTENT : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}

