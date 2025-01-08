package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.common.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.entity.WalkingRecordAttachment;
import com.korit.projectrrs.repositoiry.WalkingRecordAttachmentRepository;
import com.korit.projectrrs.service.WalkingRecordAttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WalkingRecordAttachmenServicetImpl implements WalkingRecordAttachmentService {
    private final WalkingRecordAttachmentRepository walkingRecordAttachmentRepository;

    @Override
    public ResponseDto<Void> deleteWalkingRecordAttachment(Long userId, Long attachmentId) {
        try {
            Optional<WalkingRecordAttachment> optionAttachment = walkingRecordAttachmentRepository.findByWRId(userId, attachmentId);

            if (optionAttachment.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_ATTACHMENT_ID);
            }

            WalkingRecordAttachment walkingRecordAttachment = optionAttachment.get();
            walkingRecordAttachmentRepository.delete(walkingRecordAttachment);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }
}
