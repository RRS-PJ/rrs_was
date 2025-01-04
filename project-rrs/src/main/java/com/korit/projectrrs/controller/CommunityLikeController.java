package com.korit.projectrrs.controller;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.communityLike.response.CommunityLikeResponseDto;
import com.korit.projectrrs.security.PrincipalUser;
import com.korit.projectrrs.service.CommunityLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/communities")
@RequiredArgsConstructor
public class CommunityLikeController {

    private final CommunityLikeService communityLikeService;

    /**
     * 좋아요 토글 엔드포인트
     *
     * @param communityId 커뮤니티 ID
     * @param principalUser 인증된 사용자 정보
     * @return 좋아요 상태 및 좋아요 수
     */
    @PostMapping("/{communityId}/like")
    public ResponseEntity<ResponseDto<Map<String, Object>>> toggleLike(
            @PathVariable Long communityId,
            @AuthenticationPrincipal PrincipalUser principalUser) {

        ResponseDto<Map<String, Object>> response = communityLikeService.toggleLike(principalUser.getUser(), communityId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(response, status);
    }

    /**
     * 특정 커뮤니티에 좋아요를 누른 사용자 목록 조회 엔드포인트
     *
     * @param communityId 커뮤니티 ID
     * @param principalUser 인증된 사용자 정보 (추가)
     * @return 커뮤니티 ID와 사용자 닉네임 목록
     */
    @GetMapping("/{communityId}/likes")
    public ResponseEntity<ResponseDto<List<CommunityLikeResponseDto>>> getUsersWhoLikedCommunity(
            @PathVariable Long communityId,
            @AuthenticationPrincipal PrincipalUser principalUser) {

        // principalUser.getUser()를 활용하여 인증된 사용자 정보로 추가 처리 가능
        ResponseDto<List<CommunityLikeResponseDto>> response = communityLikeService.getUsersWhoLikedCommunity(communityId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(response, status);
    }
}
