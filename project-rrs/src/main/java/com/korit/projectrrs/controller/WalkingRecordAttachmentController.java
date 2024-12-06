//package com.korit.projectrrs.controller;
//
//import com.korit.projectrrs.common.ApiMappingPattern;
//import com.korit.projectrrs.dto.ResponseDto;
//import com.korit.projectrrs.dto.walkingRecordAttachment.response.WalkingRecordAttachmentResponseDto;
//import com.korit.projectrrs.service.WalkingRecordAttachmentService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.List;
//
//@RestController
//@RequestMapping(ApiMappingPattern.WALKING_RECORD_ATTACHMENT)
//@RequiredArgsConstructor
//public class WalkingRecordAttachmentController {
//
//    private final WalkingRecordAttachmentService walkingRecordAttachmentService;
//
//    private static final String WALKING_RECORD_ATTACHMENT_DELETE = "{attachmentId}";
//
//    @PostMapping
//    public ResponseEntity<ResponseDto<List<WalkingRecordAttachmentResponseDto>>> createWalkingRecordAttachment(
//            @AuthenticationPrincipal String userId,
//            @PathVariable long petProfileId,
//            @PathVariable long walkingRecordId,
//            @RequestPart("file") List<MultipartFile> files
//    ) {
//        ResponseDto<List<WalkingRecordAttachmentResponseDto>> response = walkingRecordAttachmentService.createWalkingRecordAttachment(userId, petProfileId, walkingRecordId, files);
//        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
//        return ResponseEntity.status(status).body(response);
//    }
//
//    @DeleteMapping(WALKING_RECORD_ATTACHMENT_DELETE)
//    public ResponseEntity<ResponseDto<Void>> deleteWalkingRecord(
//            @AuthenticationPrincipal String userId,
//            @PathVariable long petProfileId,
//            @PathVariable long walkingRecordId
//    ) {
//        ResponseDto<Void> response = walkingRecordAttachmentService.deleteWalkingRecord(userId, petProfileId, walkingRecordId);
//        HttpStatus status = response.isResult() ? HttpStatus.NO_CONTENT : HttpStatus.BAD_REQUEST;
//        return ResponseEntity.status(status).body(response);
//    }
//}
