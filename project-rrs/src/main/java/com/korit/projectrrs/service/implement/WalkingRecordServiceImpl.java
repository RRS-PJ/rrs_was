package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.common.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.walkingRecord.request.UpdateWalkingRecordRequestDto;
import com.korit.projectrrs.dto.walkingRecord.request.WalkingRecordRequestDto;
import com.korit.projectrrs.dto.walkingRecord.response.WalkingRecordListResponseDto;
import com.korit.projectrrs.dto.walkingRecord.response.WalkingRecordResponseDto;
import com.korit.projectrrs.entity.*;
import com.korit.projectrrs.repositoiry.PetProfileRepository;
import com.korit.projectrrs.repositoiry.WalkingRecordAttachmentRepository;
import com.korit.projectrrs.repositoiry.WalkingRecordRepository;
import com.korit.projectrrs.service.WalkingRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WalkingRecordServiceImpl implements WalkingRecordService {

    private final PetProfileRepository petProfileRepository;
    private final WalkingRecordRepository walkingRecordRepository;
    private final WalkingRecordAttachmentRepository walkingRecordAttachmentRepository;

    @Override
    public ResponseDto<WalkingRecordResponseDto> createWalkingRecord(String userId, long petProfileId, WalkingRecordRequestDto dto) {
        WalkingRecordWeatherState walkingRecordWeatherState = dto.getWalkingRecordWeatherState();
        Integer walkingRecordDistance = dto.getWalkingRecordDistance();
        Integer hours = dto.getWalkingRecordWalkingHours();
        Integer minutes = dto.getWalkingRecordWalkingMinutes();
        LocalDate walkingRecordCreateAt = dto.getWalkingRecordCreateAt();
        String walkingRecordMemo = dto.getWalkingRecordMemo();
        List<WalkingRecordAttachment> walkingRecordAttachments = dto.getWalkingRecordAttachments();

        WalkingRecordResponseDto data = null;

        if (walkingRecordDistance == null || walkingRecordDistance <= 0) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_PET_NAME);
        }

        if (hours < 0 || minutes == null || minutes < 0 || minutes >= 60) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_WALKING_WALKING_TIME);
        }

        int totalMinutes = hours * 60 + minutes;

        if (walkingRecordCreateAt == null) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_WALKING_RECORD_CREATE_AT);
        }

        LocalDate now = LocalDate.now();
        if (walkingRecordCreateAt.isAfter(now)) {
            return ResponseDto.setFailed(ResponseMessage.TIME_IN_FUTURE_NOT_ALLOWED);
        }

        try {
            Optional<PetProfile> optionalPetProfile = petProfileRepository.findPetByUserId(userId, petProfileId);

            if (optionalPetProfile.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_PET_ID);
            }

            PetProfile petProfile = optionalPetProfile.get();

            WalkingRecord walkingRecord = WalkingRecord.builder()
                    .petProfile(petProfile)
                    .walkingRecordWeatherState(walkingRecordWeatherState != null ? walkingRecordWeatherState : WalkingRecordWeatherState.SUNNY)
                    .walkingRecordDistance(walkingRecordDistance)
                    .walkingRecordWalkingTime(totalMinutes)
                    .walkingRecordCreateAt(walkingRecordCreateAt)
                    .walkingRecordMemo(walkingRecordMemo)
                    .walkingRecordAttachments(walkingRecordAttachments)
                    .build();

            walkingRecordRepository.save(walkingRecord);

            data = new WalkingRecordResponseDto(walkingRecord);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<List<WalkingRecordListResponseDto>> getWalkingRecordList(String userId, long petProfileId, LocalDate walkingRecordCreateAt) {
        return null;
    }

    @Override
    public ResponseDto<WalkingRecordResponseDto> getWalkingRecord(String userId, long petProfileId, long walkingRecordId) {
        return null;
    }

    @Override
    public ResponseDto<WalkingRecordResponseDto> updateWalkingRecord(String userId, long walkingRecordId, UpdateWalkingRecordRequestDto dto) {
        return null;
    }

    @Override
    public ResponseDto<Void> deleteWalkingRecord(String userId, long walkingRecordId) {
        return null;
    }





}
