package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.communityAttachment.response.CommunityAttachmentDTO;

import java.util.List;

public interface CommunityAttachmentService {

    // 특정 커뮤니티의 첨부파일 조회 (DTO로 반환)
    ResponseDto<List<CommunityAttachmentDTO>> getAttachmentsByCommunityId(Long communityId);

    // 특정 첨부파일 삭제
    void deleteAttachmentById(Long attachmentId);

    // 특정 커뮤니티의 모든 첨부파일 삭제
    void deleteAttachmentsByCommunityId(Long communityId);
}
