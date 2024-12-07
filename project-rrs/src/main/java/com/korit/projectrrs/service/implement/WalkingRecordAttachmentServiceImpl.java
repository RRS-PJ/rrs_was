package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.common.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.walkingRecordAttachment.response.WalkingRecordAttachmentResponseDto;
import com.korit.projectrrs.entity.WalkingRecord;
import com.korit.projectrrs.entity.WalkingRecordAttachment;
import com.korit.projectrrs.repositoiry.WalkingRecordAttachmentRepository;
import com.korit.projectrrs.repositoiry.WalkingRecordRepository;
import com.korit.projectrrs.service.WalkingRecordAttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WalkingRecordAttachmentServiceImpl implements WalkingRecordAttachmentService {

    private final WalkingRecordRepository walkingRecordRepository;
    private final WalkingRecordAttachmentRepository walkingRecordAttachmentRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public ResponseDto<List<WalkingRecordAttachmentResponseDto>> createWalkingRecordAttachment(Long userId, Long petId, Long walkingRecordId, List<MultipartFile> files) {
        List<WalkingRecordAttachmentResponseDto> data = new ArrayList<>();

        if (files == null || files.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_FILE);
        }

        try {
            Optional<WalkingRecord> optionalWalkingRecord = walkingRecordRepository.findWalkingRecordBytPetIdAndWalkingRecordId(userId, petId, walkingRecordId);

            if (optionalWalkingRecord.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_WALKING_RECORD_ID);
            }

            for (MultipartFile file : files) {
                String originalFileName = file.getOriginalFilename();

                if (originalFileName != null && !originalFileName.isEmpty()) {
                    String fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1).toLowerCase();

                    if (!fileExtension.equals("png") && !fileExtension.equals("jpg")) {
                        return ResponseDto.setFailed(ResponseMessage.INVALID_FILE);
                    }

                    String sanitizedFileName = originalFileName.replaceAll("[\\x00-\\x1F\\x7F]", "_");
                    String fileName = System.currentTimeMillis() + "_" + sanitizedFileName;

                    Path path = Paths.get(uploadDir, fileName);

                    Files.copy(file.getInputStream(), path);

                    WalkingRecordAttachment walkingRecordAttachment = WalkingRecordAttachment.builder()
                            .walkingRecordId(walkingRecordId)
                            .walkingRecordAttachmentFile(path.toString())
                            .build();

                    walkingRecordAttachmentRepository.save(walkingRecordAttachment);

                    WalkingRecordAttachmentResponseDto attachmentResponseDto = WalkingRecordAttachmentResponseDto.builder()
                            .fileName(Paths.get(walkingRecordAttachment.getWalkingRecordAttachmentFile()).getFileName().toString())
                            .fileUrl(walkingRecordAttachment.getWalkingRecordAttachmentFile())
                            .build();

                    data.add(attachmentResponseDto);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.FILE_UPLOAD_ERROR);  // 파일 업로드, 복사, 네트워크 연결 문제의 에러
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<Void> deleteWalkingRecord(Long userId, Long petProfileId, Long walkingRecordId) {
        return null;
    }
}
