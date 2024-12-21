package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.communitycomment.request.CommunityCommentCreateRequestDto;
import com.korit.projectrrs.dto.communitycomment.request.CommunityCommentUpdateRequestDto;
import com.korit.projectrrs.dto.communitycomment.response.CommunityCommentResponseDto;

import java.util.List;

public interface CommunityCommentService {
    // 댓글 생성
    ResponseDto<CommunityCommentResponseDto> createComment(Long userId, CommunityCommentCreateRequestDto dto);

    // 댓글 수정
    ResponseDto<CommunityCommentResponseDto> updateComment(Long userId, CommunityCommentUpdateRequestDto dto);

    // 특정 커뮤니티의 모든 댓글 조회
    List<CommunityCommentResponseDto> getCommentsByCommunity(Long communityId);

    // 댓글 삭제
    void deleteComment(Long userId, Long commentId);
}
