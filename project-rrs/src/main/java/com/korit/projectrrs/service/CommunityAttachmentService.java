package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.communityAttachment.response.CommunityAttachmentDTO;

import java.util.List;

public interface CommunityAttachmentService {
    ResponseDto<List<CommunityAttachmentDTO>> getAttachmentsByCommunityId(Long communityId);
    void deleteAttachmentById(Long attachmentId);
    void deleteAttachmentsByCommunityId(Long communityId);
}
