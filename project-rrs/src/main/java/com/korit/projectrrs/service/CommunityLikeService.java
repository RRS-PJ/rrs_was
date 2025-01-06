package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.communityLike.response.CommunityLikeResponseDto;
import com.korit.projectrrs.entity.User;

import java.util.List;
import java.util.Map;

public interface CommunityLikeService {
    /**
     * 특정 커뮤니티에 좋아요 토글
     *
     * @param user 사용자 엔티티
     * @param communityId 커뮤니티 ID
     * @return 좋아요 상태 및 좋아요 수
     */
    ResponseDto<Map<String, Object>> toggleLike(User user, Long communityId);

    /**
     * 특정 커뮤니티에 좋아요를 누른 사용자 목록 조회
     *
     * @param communityId 커뮤니티 ID
     * @return 커뮤니티 ID와 사용자 닉네임 목록
     */
    ResponseDto<List<CommunityLikeResponseDto>> getUsersWhoLikedCommunity(Long communityId);
}
