package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.healthrecord.request.HealthRecordCreateRequestDto;
import com.korit.projectrrs.dto.healthrecord.request.HealthRecordUpdateRequestDto;
import com.korit.projectrrs.dto.healthrecord.response.HealthRecordAllResponseDto;
import com.korit.projectrrs.dto.healthrecord.response.HealthRecordResponseDto;

import java.util.List;

public interface HealthRecordService {
    ResponseDto<HealthRecordResponseDto> createHealthRecord(Long userId, Long petId, HealthRecordCreateRequestDto requestDto);
    ResponseDto<HealthRecordResponseDto> updateHealthRecord(Long userId, Long petId, Long healthRecordId, HealthRecordUpdateRequestDto requestDto);
    ResponseDto<Void> deleteHealthRecord(Long userId, Long petId, Long healthRecordId);
    ResponseDto<List<HealthRecordAllResponseDto>> getAllHealthRecords(Long userId, Long petId);
    ResponseDto<HealthRecordResponseDto> getHealthRecord(Long userId, Long petId, Long healthRecordId);
    ResponseDto<List<HealthRecordAllResponseDto>> getAllHealthRecordsByUserId(Long userId);
}
