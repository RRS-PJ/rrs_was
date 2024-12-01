//package com.korit.projectrrs.service.implement;
//
//import com.korit.projectrrs.common.ResponseMessage;
//import com.korit.projectrrs.dto.ResponseDto;
//import com.korit.projectrrs.dto.walkingRecord.response.WalkingRecordResponseDto;
//import com.korit.projectrrs.dto.walkingRecordAttachment.responseDto.WalkingRecordAttachmentResponseDto;
//import com.korit.projectrrs.entity.WalkingRecord;
//import com.korit.projectrrs.entity.WalkingRecordAttachment;
//import com.korit.projectrrs.repositoiry.WalkingRecordAttachmentRepository;
//import com.korit.projectrrs.repositoiry.WalkingRecordRepository;
//import com.korit.projectrrs.service.WalkingRecordAttachmentService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class WalkingRecordAttachmentServiceImpl implements WalkingRecordAttachmentService {
//
//    private final WalkingRecordRepository walkingRecordRepository;
//    private final WalkingRecordAttachmentRepository walkingRecordAttachmentRepository;
//
//    @Value("${file.upload-dir}")
//    private String uploadDir;
//
//    @Override
//    public ResponseDto<WalkingRecordAttachmentResponseDto> createWalkingRecordAttachment(String userId, long walkingRecordId, List<MultipartFile> file) {
//
//        WalkingRecordResponseDto data = null;
//
//        try {
//            Optional<WalkingRecord> optionalWalkingRecord = walkingRecordRepository.findByWalkingRecordId(walkingRecordId);
//
//            if (optionalWalkingRecord.isEmpty()) {
//                return ResponseDto.setFailed(ResponseMessage.INVALID_WALKING_RECORD_ID);
//            }
//
//            WalkingRecord walkingRecord = optionalWalkingRecord.get();
//
//            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
//            Path path = Paths.get(uploadDir, fileName);
//
//            Files.copy(file.getInputStream(), path);
//
//            WalkingRecordAttachment walkingRecordAttachment = new WalkingRecordAttachment();
//            walkingRecordAttachment.setWalkingRecord(walkingRecord);
//            walkingRecordAttachment.setWalkingRecordAttachmentFile(path.toString());
//
//            walkingRecordAttachmentRepository.save(walkingRecordAttachment);
//
//            data = new WalkingRecordResponseDto(walkingRecord);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
//        }
//        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
//    }
//
//
//    @Override
//    public ResponseDto<Void> deleteWalkingRecord(String userId, long walkingRecordId) {
//        return null;
//    }
//}
