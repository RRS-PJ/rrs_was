package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.community.request.CommunityCreateRequestDto;
import com.korit.projectrrs.dto.community.request.CommunityUpdateRequestDto;
import com.korit.projectrrs.dto.community.response.CommunityResponseAllDto;
import com.korit.projectrrs.dto.community.response.CommunityResponseDto;

import java.util.List;
import java.util.Map;

public interface CommunityService {
    ResponseDto<CommunityResponseDto> createCommunity(Long userId, CommunityCreateRequestDto dto);

    ResponseDto<CommunityResponseDto> updateCommunity(Long userId, Long communityId, CommunityUpdateRequestDto dto);

    ResponseDto<Void> deleteCommunity(Long userId, Long communityId);

    ResponseDto<List<CommunityResponseAllDto>> getAllCommunities();

    ResponseDto<CommunityResponseDto> getCommunity(Long communityId);

    ResponseDto<Map<String, Object>> toggleLike(Long userId, Long communityId);
}
