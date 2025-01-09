package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.entity.CommunityAttachment;
import com.korit.projectrrs.repositoiry.CommunityAttachmentRepository;
import com.korit.projectrrs.service.CommunityAttachmentService;
import com.korit.projectrrs.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityAttachmentServiceImpl implements CommunityAttachmentService {

    private final CommunityAttachmentRepository communityAttachmentRepository;
    private final FileService fileService;

    @Override
    public List<CommunityAttachment> getAttachmentsByCommunityId(Long communityId) {
        // 특정 커뮤니티의 첨부파일 목록 조회
        return communityAttachmentRepository.findByCommunityCommunityId(communityId);
    }

    @Override
    public void deleteAttachmentById(Long attachmentId) {
        // 특정 첨부파일 삭제
        CommunityAttachment attachment = communityAttachmentRepository.findById(attachmentId)
                .orElseThrow(() -> new RuntimeException("Attachment not found"));

        // 파일 삭제
        if (attachment.getCommunityAttachment() != null) {
            fileService.removeFile(attachment.getCommunityAttachment());
        }

        // 데이터베이스에서 삭제
        communityAttachmentRepository.deleteById(attachmentId);
    }

    @Override
    public void deleteAttachmentsByCommunityId(Long communityId) {
        // 특정 커뮤니티의 모든 첨부파일 삭제
        List<CommunityAttachment> attachments = communityAttachmentRepository.findByCommunityCommunityId(communityId);

        for (CommunityAttachment attachment : attachments) {
            if (attachment.getCommunityAttachment() != null) {
                fileService.removeFile(attachment.getCommunityAttachment());
            }
        }

        // 데이터베이스에서 첨부파일 삭제
        communityAttachmentRepository.deleteAll(communityId);
    }
}
