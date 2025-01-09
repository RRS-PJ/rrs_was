package com.korit.projectrrs.service;

import com.korit.projectrrs.entity.CommunityAttachment;

import java.util.List;

public interface CommunityAttachmentService {
    List<CommunityAttachment> getAttachmentsByCommunityId(Long communityId);

    void deleteAttachmentById(Long attachmentId);

    void deleteAttachmentsByCommunityId(Long communityId);
}
