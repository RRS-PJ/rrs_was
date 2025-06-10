package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.communityLike.response.CommunityLikeResponseDto;
import com.korit.projectrrs.entity.User;

import java.util.List;
import java.util.Map;

public interface CommunityLikeService {
    ResponseDto<Map<String, Object>> toggleLike(User user, Long communityId);
    ResponseDto<List<CommunityLikeResponseDto>> getUsersWhoLikedCommunity(Long communityId);
}
