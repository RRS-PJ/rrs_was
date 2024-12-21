package com.korit.projectrrs.controller;

import com.korit.projectrrs.common.ApiMappingPattern;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.walkingRecord.request.UpdateWalkingRecordRequestDto;
import com.korit.projectrrs.dto.walkingRecord.request.WalkingRecordRequestDto;
import com.korit.projectrrs.dto.walkingRecord.response.WalkingRecordListResponseDto;
import com.korit.projectrrs.dto.walkingRecord.response.WalkingRecordResponseDto;
import com.korit.projectrrs.security.PrincipalUser;
import com.korit.projectrrs.service.WalkingRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.WALKING_RECORD)
@RequiredArgsConstructor
public class WalkingRecordController {

    private final WalkingRecordService walkingRecordService;

    private static final String WALKING_RECORD_POST= "/petId/{petId}";
    private static final String WALKING_RECORD_GET_LIST = "/petId/{petId}/walkingRecordCreateAt/{walkingRecordCreateAt}";
    private static final String WALKING_RECORD_GET_BY_ID = "/petId/{petId}/walkingRecordId/{walkingRecordId}";
    private static final String WALKING_RECORD_PUT = "/petId/{petId}/walkingRecordId/{walkingRecordId}";
    private static final String WALKING_RECORD_DELETE = "/petId/{petId}/walkingRecordId/{walkingRecordId}";

    @PostMapping(WALKING_RECORD_POST)
    public ResponseEntity<ResponseDto<WalkingRecordResponseDto>> createWalkingRecord(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long petId,
            @RequestParam("files") List<MultipartFile> files,  // 파일을 받는 파라미터
            @ModelAttribute WalkingRecordRequestDto dto
    ) {
        if (files != null && !files.isEmpty()) {
            dto.setFiles(files);
        }

        Long userId = principalUser.getUser().getUserId();
        ResponseDto<WalkingRecordResponseDto> response = walkingRecordService.createWalkingRecord(userId, petId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(WALKING_RECORD_GET_LIST)
    public ResponseEntity<ResponseDto<List<WalkingRecordListResponseDto>>> getWalkingRecordList(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long petId,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate walkingRecordCreateAt
    ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<List<WalkingRecordListResponseDto>> response = walkingRecordService.getWalkingRecordList(userId, petId, walkingRecordCreateAt);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(WALKING_RECORD_GET_BY_ID)
    public ResponseEntity<ResponseDto<WalkingRecordResponseDto>> getWalkingRecordInfo(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long petId,
            @PathVariable Long walkingRecordId
    ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<WalkingRecordResponseDto> response = walkingRecordService.getWalkingRecordInfo(userId, petId, walkingRecordId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping(WALKING_RECORD_PUT)
    public ResponseEntity<ResponseDto<WalkingRecordResponseDto>> updateWalkingRecord(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long petId,
            @PathVariable Long walkingRecordId,
            @RequestBody UpdateWalkingRecordRequestDto dto
    ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<WalkingRecordResponseDto> response = walkingRecordService.updateWalkingRecord(userId, petId, walkingRecordId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping(WALKING_RECORD_DELETE)
    public ResponseEntity<ResponseDto<Void>> deleteWalkingRecord(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long petId,
            @PathVariable Long walkingRecordId
    ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<Void> response = walkingRecordService.deleteWalkingRecord(userId, petId, walkingRecordId);
        HttpStatus status = response.isResult() ? HttpStatus.NO_CONTENT : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}
