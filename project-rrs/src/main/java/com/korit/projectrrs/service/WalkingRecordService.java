package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.walkingRecord.request.UpdateWalkingRecordRequestDto;
import com.korit.projectrrs.dto.walkingRecord.request.WalkingRecordRequestDto;
import com.korit.projectrrs.dto.walkingRecord.response.WalkingRecordListResponseDto;
import com.korit.projectrrs.dto.walkingRecord.response.WalkingRecordResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface WalkingRecordService {
    ResponseDto<WalkingRecordResponseDto> createWalkingRecord(Long userId, Long petId, WalkingRecordRequestDto dto);
    ResponseDto<List<WalkingRecordListResponseDto>> getWalkingRecordList(Long userId, Long petId, LocalDate walkingRecordCreateAt);
    ResponseDto<WalkingRecordResponseDto> getWalkingRecordInfo(Long userId, Long petId, Long walkingRecordId);
    ResponseDto<WalkingRecordResponseDto> updateWalkingRecord(Long userId, Long petId, Long walkingRecordId, UpdateWalkingRecordRequestDto dto);
    ResponseDto<Void> deleteWalkingRecord(Long userId, Long petId, Long walkingRecordId);
    ResponseDto<List<WalkingRecordListResponseDto>> getWalkingRecordAll(Long userId);
}
