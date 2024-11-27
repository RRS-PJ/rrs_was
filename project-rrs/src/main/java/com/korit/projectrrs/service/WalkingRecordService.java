package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.walkingRecord.request.UpdateWalkingRecordRequestDto;
import com.korit.projectrrs.dto.walkingRecord.request.WalkingRecordRequestDto;
import com.korit.projectrrs.dto.walkingRecord.response.WalkingRecordListResponseDto;
import com.korit.projectrrs.dto.walkingRecord.response.WalkingRecordResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface WalkingRecordService {
    ResponseDto<WalkingRecordResponseDto> createWalkingRecord(String userId, WalkingRecordRequestDto dto);
    ResponseDto<List<WalkingRecordListResponseDto>> getWalkingRecordList(String userId, Long petProfileId, LocalDate walkingRecordCreateAt);
    ResponseDto<WalkingRecordResponseDto> getWalkingRecord(String userId, Long petProfileId, Long walkingRecordId);
    ResponseDto<WalkingRecordResponseDto> updateWalkingRecord(String userId, Long walkingRecordId, UpdateWalkingRecordRequestDto dto);
    ResponseDto<Void> deleteWalkingRecord(String userId, Long petProfileId, Long walkingRecordId);
}
