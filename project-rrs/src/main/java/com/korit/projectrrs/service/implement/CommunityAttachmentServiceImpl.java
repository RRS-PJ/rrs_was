package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.communityAttachment.response.CommunityAttachmentDTO;
import com.korit.projectrrs.entity.CommunityAttachment;
import com.korit.projectrrs.repositoiry.CommunityAttachmentRepository;
import com.korit.projectrrs.service.CommunityAttachmentService;
import com.korit.projectrrs.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityAttachmentServiceImpl implements CommunityAttachmentService {

    private final CommunityAttachmentRepository communityAttachmentRepository;
    private final FileService fileService;

    @Override
    public ResponseDto<List<CommunityAttachmentDTO>> getAttachmentsByCommunityId(Long communityId) {

        List<CommunityAttachment> attachments = communityAttachmentRepository.findByCommunityCommunityId(communityId);
        List<CommunityAttachmentDTO> attachmentDTOs = attachments.stream()
                .map(attachment -> new CommunityAttachmentDTO(
                        attachment.getCommunityAttachmentId(),
                        attachment.getCommunityAttachment()
                ))
                .collect(Collectors.toList());

        return ResponseDto.setSuccess("Attachments fetched successfully", attachmentDTOs);
    }

    @Override
    public void deleteAttachmentById(Long attachmentId) {

        CommunityAttachment attachment = communityAttachmentRepository.findById(attachmentId)
                .orElseThrow(() -> new RuntimeException("Attachment not found"));

        if (attachment.getCommunityAttachment() != null) {
            fileService.removeFile(attachment.getCommunityAttachment());
        }

        communityAttachmentRepository.deleteById(attachmentId);
    }

    @Override
    public void deleteAttachmentsByCommunityId(Long communityId) {

        List<CommunityAttachment> attachments = communityAttachmentRepository.findByCommunityCommunityId(communityId);

        for (CommunityAttachment attachment : attachments) {
            if (attachment.getCommunityAttachment() != null) {
                fileService.removeFile(attachment.getCommunityAttachment());
            }
        }

        communityAttachmentRepository.deleteAll(attachments);
    }
}
