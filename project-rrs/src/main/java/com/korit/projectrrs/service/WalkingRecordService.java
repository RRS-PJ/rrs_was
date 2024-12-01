package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.walkingRecord.request.UpdateWalkingRecordRequestDto;
import com.korit.projectrrs.dto.walkingRecord.request.WalkingRecordRequestDto;
import com.korit.projectrrs.dto.walkingRecord.response.WalkingRecordListResponseDto;
import com.korit.projectrrs.dto.walkingRecord.response.WalkingRecordResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public interface WalkingRecordService {
    ResponseDto<WalkingRecordResponseDto> createWalkingRecord(String userId, long petProfileId, WalkingRecordRequestDto dto);
    ResponseDto<List<WalkingRecordListResponseDto>> getWalkingRecordList(String userId, long petProfileId, LocalDate walkingRecordCreateAt);
    ResponseDto<WalkingRecordResponseDto> getWalkingRecord(String userId, long petProfileId, long walkingRecordId);
    ResponseDto<WalkingRecordResponseDto> updateWalkingRecord(String userId, long walkingRecordId, UpdateWalkingRecordRequestDto dto);
    ResponseDto<Void> deleteWalkingRecord(String userId, long walkingRecordId);
}
