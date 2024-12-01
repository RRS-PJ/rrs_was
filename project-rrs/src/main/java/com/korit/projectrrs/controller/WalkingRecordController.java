package com.korit.projectrrs.controller;

import com.korit.projectrrs.common.ApiMappingPattern;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.walkingRecord.request.UpdateWalkingRecordRequestDto;
import com.korit.projectrrs.dto.walkingRecord.request.WalkingRecordRequestDto;
import com.korit.projectrrs.dto.walkingRecord.response.WalkingRecordListResponseDto;
import com.korit.projectrrs.dto.walkingRecord.response.WalkingRecordResponseDto;
import com.korit.projectrrs.service.WalkingRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.WALKING_RECORD)
@RequiredArgsConstructor
public class WalkingRecordController {

    private final WalkingRecordService walkingRecordService;

    private static final String WALKING_RECORD_POST= "/petProfileId/{petProfileId}";
    private static final String WALKING_RECORD_GET_BY_ID = "/petProfileId/{petProfileId}/walkingRecordId/{walkingRecordId}";
    private static final String WALKING_RECORD_GET_LIST = "/petProfileId/{petProfileId}/walkingRecordCreateAt/{walkingRecordCreateAt}";
    private static final String WALKING_RECORD_PUT = "/petProfileId/{petProfileId}/walkingRecordId/{walkingRecordId}";
    private static final String WALKING_RECORD_DELETE = "/petProfileId/{petProfileId}/walkingRecordId/{walkingRecordId}";
    private static final String WALKING_RECORD_ATTACHMENT_POST = "/walkingRecordId/{walkingRecordId}/attachments";

    @PostMapping(WALKING_RECORD_POST)
    public ResponseEntity<ResponseDto<WalkingRecordResponseDto>> createWalkingRecord(
            @AuthenticationPrincipal String userId,
            @PathVariable long petProfileId,
            @RequestBody WalkingRecordRequestDto dto
    ) {
        ResponseDto<WalkingRecordResponseDto> response = walkingRecordService.createWalkingRecord(userId, petProfileId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(WALKING_RECORD_GET_LIST)
    public ResponseEntity<ResponseDto<List<WalkingRecordListResponseDto>>> getWalkingRecordList(
            @AuthenticationPrincipal String userId,
            @PathVariable long petProfileId,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate walkingRecordCreateAt
    ) {
        ResponseDto<List<WalkingRecordListResponseDto>> response = walkingRecordService.getWalkingRecordList(userId, petProfileId, walkingRecordCreateAt);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(WALKING_RECORD_GET_BY_ID)
    public ResponseEntity<ResponseDto<WalkingRecordResponseDto>> getWalkingRecord(
            @AuthenticationPrincipal String userId,
            @PathVariable long petProfileId,
            @PathVariable long walkingRecordId
    ) {
        ResponseDto<WalkingRecordResponseDto> response = walkingRecordService.getWalkingRecord(userId, petProfileId, walkingRecordId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping(WALKING_RECORD_PUT)
    public ResponseEntity<ResponseDto<WalkingRecordResponseDto>> updateWalkingRecord(
            @AuthenticationPrincipal String userId,
            @PathVariable long walkingRecordId,
            @RequestBody UpdateWalkingRecordRequestDto dto
    ) {
        ResponseDto<WalkingRecordResponseDto> response = walkingRecordService.updateWalkingRecord(userId, walkingRecordId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping(WALKING_RECORD_DELETE)
    public ResponseEntity<ResponseDto<Void>> deleteWalkingRecord(
            @AuthenticationPrincipal String userId,
            @PathVariable long walkingRecordId
    ) {
        ResponseDto<Void> response = walkingRecordService.deleteWalkingRecord(userId, walkingRecordId);
        HttpStatus status = response.isResult() ? HttpStatus.NO_CONTENT : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}
