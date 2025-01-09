package com.korit.projectrrs.controller;

import com.korit.projectrrs.common.ApiMappingPattern;
import com.korit.projectrrs.common.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.community.request.CommunityCreateRequestDto;
import com.korit.projectrrs.dto.community.request.CommunityUpdateRequestDto;
import com.korit.projectrrs.dto.community.response.CommunityResponseAllDto;
import com.korit.projectrrs.dto.community.response.CommunityResponseDto;
import com.korit.projectrrs.security.PrincipalUser;
import com.korit.projectrrs.service.CommunityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.korit.projectrrs.common.ApiMappingPattern.*;

@RestController
@RequestMapping(ApiMappingPattern.COMMUNITY)
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;

    @PostMapping(COMMUNITY_POST)
    public ResponseEntity<ResponseDto<CommunityResponseDto>> createCommunity(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @ModelAttribute @Valid CommunityCreateRequestDto dto
    ) {
        if (principalUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ResponseDto.setFailed(ResponseMessage.USER_NOT_AUTHENTICATED));
        }
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<CommunityResponseDto> response = communityService.createCommunity(userId, dto);
        if (response.getMessage().equals(ResponseMessage.NOT_AUTHORIZED_TO_CREATE)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
        return ResponseEntity.status(response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(response);
    }

    @PutMapping(COMMUNITY_PUT)
    public ResponseEntity<ResponseDto<CommunityResponseDto>> updateCommunity(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long communityId,
            @ModelAttribute @Valid CommunityUpdateRequestDto dto
    ) {
        if (principalUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ResponseDto.setFailed(ResponseMessage.USER_NOT_AUTHENTICATED));
        }
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<CommunityResponseDto> response = communityService.updateCommunity(userId, communityId, dto);
        if (response.getMessage().equals(ResponseMessage.NOT_AUTHORIZED_TO_UPDATE)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
        return ResponseEntity.status(response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(response);
    }

    @GetMapping(COMMUNITY_GET_BY_ID)
    public ResponseEntity<ResponseDto<CommunityResponseDto>> getCommunity(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long communityId
    ) {
        if (principalUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ResponseDto.setFailed(ResponseMessage.USER_NOT_AUTHENTICATED));
        }
        ResponseDto<CommunityResponseDto> response = communityService.getCommunity(communityId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<CommunityResponseAllDto>>> getAllCommunities() {
        ResponseDto<List<CommunityResponseAllDto>> response = communityService.getAllCommunities();
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NO_CONTENT;
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping(COMMUNITY_DELETE)
    public ResponseEntity<ResponseDto<Void>> deleteCommunity(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long communityId
    ) {
        if (principalUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ResponseDto.setFailed(ResponseMessage.USER_NOT_AUTHENTICATED));
        }
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<Void> response = communityService.deleteCommunity(userId, communityId);
        if (response.getMessage().equals(ResponseMessage.NOT_AUTHORIZED_TO_DELETE)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
        HttpStatus status = response.isResult() ? HttpStatus.NO_CONTENT : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}