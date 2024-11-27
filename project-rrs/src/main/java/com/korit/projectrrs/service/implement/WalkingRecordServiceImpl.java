package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.common.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.walkingRecord.request.UpdateWalkingRecordRequestDto;
import com.korit.projectrrs.dto.walkingRecord.request.WalkingRecordRequestDto;
import com.korit.projectrrs.dto.walkingRecord.response.WalkingRecordListResponseDto;
import com.korit.projectrrs.dto.walkingRecord.response.WalkingRecordResponseDto;
import com.korit.projectrrs.entity.PetProfile;
import com.korit.projectrrs.entity.WalkingRecordWeatherState;
import com.korit.projectrrs.repositoiry.PetProfileRepository;
import com.korit.projectrrs.repositoiry.UserRepository;
import com.korit.projectrrs.repositoiry.WalkingRecordRepository;
import com.korit.projectrrs.service.WalkingRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WalkingRecordServiceImpl implements WalkingRecordService {

    private final WalkingRecordRepository walkingRecordRepository;
    private final UserRepository userRepository;
    private final PetProfileRepository petProfileRepository;

    @Override
    public ResponseDto<WalkingRecordResponseDto> createWalkingRecord(String userId, WalkingRecordRequestDto dto) {
        PetProfile petProfile = dto.getPetProfile();
        WalkingRecordWeatherState walkingRecordWeatherState = dto.getWalkingRecordWeatherState();
        Integer walkingRecordDistance = dto.getWalkingRecordDistance();
        LocalTime walkingRecordWalkingTime = dto.getWalkingRecordWalkingTime();
        LocalDate walkingRecordCreateAt = dto.getWalkingRecordCreateAt();
        String walkingRecordMemo = dto.getWalkingRecordMemo();
        List<String> walkingRecordAttachments = dto.getWalkingRecordAttachments();

        WalkingRecordResponseDto response = null;

        if (walkingRecordDistance == null || walkingRecordDistance <= 0) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_PET_NAME);
        }

        if (walkingRecordCreateAt == null) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_WALKING_RECORD_CREATE_AT);
        }

        LocalDate now = LocalDate.now();
        if (walkingRecordCreateAt.isAfter(now)) {
            return ResponseDto.setFailed(ResponseMessage.TIME_IN_FUTURE_NOT_ALLOWED);
        }



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
