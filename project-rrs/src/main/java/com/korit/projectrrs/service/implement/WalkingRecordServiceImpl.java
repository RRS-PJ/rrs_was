package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.walkingRecord.request.UpdateWalkingRecordRequestDto;
import com.korit.projectrrs.dto.walkingRecord.request.WalkingRecordRequestDto;
import com.korit.projectrrs.dto.walkingRecord.response.WalkingRecordListResponseDto;
import com.korit.projectrrs.dto.walkingRecord.response.WalkingRecordResponseDto;
import com.korit.projectrrs.repositoiry.WalkingRecordRepository;
import com.korit.projectrrs.service.WalkingRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WalkingRecordServiceImpl implements WalkingRecordService {

    private final WalkingRecordRepository walkingRecordRepository;

    @Override
    public ResponseDto<WalkingRecordResponseDto> createWalkingRecord(String userId, WalkingRecordRequestDto dto) {
        return null;
    }

    @Override
    public ResponseDto<List<WalkingRecordListResponseDto>> getWalkingRecordList(String userId, Long petProfileId, LocalDate walkingRecordCreateAt) {
        return null;
    }

    @Override
    public ResponseDto<WalkingRecordResponseDto> getWalkingRecord(String userId, Long petProfileId, Long walkingRecordId) {
        return null;
    }

    @Override
    public ResponseDto<WalkingRecordResponseDto> updateWalkingRecord(String userId, Long walkingRecordId, UpdateWalkingRecordRequestDto dto) {
        return null;
    }

    @Override
    public ResponseDto<Void> deleteWalkingRecord(String userId, Long petProfileId, Long walkingRecordId) {
        return null;
    }
}
