package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.communitycomment.request.CommunityCommentCreateRequestDto;
import com.korit.projectrrs.dto.communitycomment.request.CommunityCommentUpdateRequestDto;
import com.korit.projectrrs.dto.communitycomment.response.CommunityCommentResponseDto;

import java.util.List;

public interface CommunityCommentService {
    ResponseDto<CommunityCommentResponseDto> createComment(Long userId, CommunityCommentCreateRequestDto dto);
    ResponseDto<List<CommunityCommentResponseDto>> getCommentsByCommunity(Long communityId);
    ResponseDto<CommunityCommentResponseDto> updateComment(Long userId, CommunityCommentUpdateRequestDto dto);
    ResponseDto<Void> deleteCommentFromCommunity(Long userId, Long communityId, Long commentId);
}
