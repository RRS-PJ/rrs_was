package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.common.constant.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.walkingRecord.request.UpdateWalkingRecordRequestDto;
import com.korit.projectrrs.dto.walkingRecord.request.WalkingRecordRequestDto;
import com.korit.projectrrs.dto.walkingRecord.response.WalkingRecordListResponseDto;
import com.korit.projectrrs.dto.walkingRecord.response.WalkingRecordResponseDto;
import com.korit.projectrrs.entity.*;
import com.korit.projectrrs.repositoiry.PetRepository;
import com.korit.projectrrs.repositoiry.WalkingRecordAttachmentRepository;
import com.korit.projectrrs.repositoiry.WalkingRecordRepository;
import com.korit.projectrrs.service.FileService;
import com.korit.projectrrs.service.WalkingRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WalkingRecordServiceImpl implements WalkingRecordService {

    private final PetRepository petRepository;
    private final WalkingRecordRepository walkingRecordRepository;
    private final WalkingRecordAttachmentRepository walkingRecordAttachmentRepository;
    private final FileService fileService;

    @Override
    public ResponseDto<WalkingRecordResponseDto> createWalkingRecord(Long userId, Long petId, @Valid WalkingRecordRequestDto dto) {
        WalkingRecordWeatherState walkingRecordWeatherState = dto.getWalkingRecordWeatherState() != null
                ? dto.getWalkingRecordWeatherState()
                : WalkingRecordWeatherState.SUNNY;
        Integer walkingRecordDistance = dto.getWalkingRecordDistance();
        Integer walkingRecordWalkingTime = dto.getWalkingRecordWalkingTime();
        LocalDate walkingRecordCreateAt = dto.getWalkingRecordCreateAt();
        String walkingRecordMemo = dto.getWalkingRecordMemo();

        WalkingRecordResponseDto data = null;

        List<WalkingRecordWeatherState> validWeatherStates = Arrays.asList(WalkingRecordWeatherState.values());
        List<String> validExtensions = Arrays.asList("jpg", "png", "jpeg");

        if (!validWeatherStates.contains(walkingRecordWeatherState)) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_WALKING_RECORD_WEATHER_STATE);
        }

        if (walkingRecordDistance == null || walkingRecordDistance <= 0) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_WALKING_RECORD_DISTANCE);
        }

        if (walkingRecordWalkingTime == null || walkingRecordWalkingTime <= 0) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_WALKING_RECORD_TIME);
        }

        if (walkingRecordCreateAt == null) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_WALKING_RECORD_CREATE_AT);
        }

        LocalDate now = LocalDate.now();
        if (walkingRecordCreateAt.isAfter(now)) {
            return ResponseDto.setFailed(ResponseMessage.TIME_IN_FUTURE_NOT_ALLOWED);
        }

        try {
            Optional<Pet> optionalPet = petRepository.findPetByUserId(userId, petId);

            if (optionalPet.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_PET_ID);
            }

            Pet pet = optionalPet.get();

            WalkingRecord walkingRecord = WalkingRecord.builder()
                    .pet(pet)
                    .walkingRecordWeatherState(walkingRecordWeatherState != null ? walkingRecordWeatherState : WalkingRecordWeatherState.SUNNY)
                    .walkingRecordDistance(walkingRecordDistance)
                    .walkingRecordWalkingTime(walkingRecordWalkingTime)
                    .walkingRecordCreateAt(walkingRecordCreateAt)
                    .walkingRecordMemo(walkingRecordMemo)
                    .build();

            walkingRecordRepository.save(walkingRecord);

            // 파일 업로드 처리
            List<MultipartFile> Multifiles = dto.getFiles();
            List<String> fileNames = new ArrayList<>();

            if (Multifiles == null || Multifiles.isEmpty()) {
                Multifiles = new ArrayList<>();  // 빈 배열로 초기화
            }

            if (Multifiles != null && !Multifiles.isEmpty()) {
                for (MultipartFile file : Multifiles) {
                    String fileName = file.getOriginalFilename();
                    String fileExtension = fileName != null ? fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase() : "";

                    if (!validExtensions.contains(fileExtension)) {
                        return ResponseDto.setFailed(ResponseMessage.INVALID_FILE);
                    }
                }
            }

            for (MultipartFile multifiles : Multifiles) {
                String fileName = fileService.uploadFile(multifiles, "walking-record");
                fileNames.add(fileName);
                if (fileName != null) {
                    WalkingRecordAttachment attachment = new WalkingRecordAttachment();
                    attachment.setWalkingRecord(walkingRecord);
                    attachment.setWalkingRecordAttachmentFile(fileName);
                    walkingRecordAttachmentRepository.save(attachment);
                }
            }

            data = new WalkingRecordResponseDto(walkingRecord).toBuilder()
                    .fileName(fileNames)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<List<WalkingRecordListResponseDto>> getWalkingRecordList(Long userId, Long petId, LocalDate walkingRecordCreateAt) {
        List<WalkingRecordListResponseDto> data = new ArrayList<>();

        try {
            List<WalkingRecord> walkingRecords = walkingRecordRepository.findAllWalkingReccrdByCreateAt(petId, walkingRecordCreateAt);

            if (walkingRecords.isEmpty()) {
                return ResponseDto.setSuccess(ResponseMessage.NOT_EXIST_WALKING_RECORD_ID, data);
            }

            data = walkingRecords.stream()
                    .map(WalkingRecordListResponseDto::new)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<WalkingRecordResponseDto> getWalkingRecordInfo(Long userId, Long petId, Long walkingRecordId) {
        WalkingRecordResponseDto data = null;

        try {
            Optional<WalkingRecord> optionalWalkingRecord = walkingRecordRepository.findWalkingRecordBytPetIdAndWalkingRecordId(userId, petId, walkingRecordId);

            if (optionalWalkingRecord.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_WALKING_RECORD_ID);
            }

            WalkingRecord walkingRecord = optionalWalkingRecord.get();

            data = new WalkingRecordResponseDto(walkingRecord);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<WalkingRecordResponseDto> updateWalkingRecord(Long userId, Long petId, Long walkingRecordId, @Valid UpdateWalkingRecordRequestDto dto) {
        WalkingRecordWeatherState walkingRecordWeatherState = dto.getWalkingRecordWeatherState();
        Integer walkingRecordDistance = dto.getWalkingRecordDistance();
        Integer walkingRecordWalkingTime = dto.getWalkingRecordWalkingTime();
        LocalDate walkingRecordCreateAt = dto.getWalkingRecordCreateAt();
        String walkingRecordMemo = dto.getWalkingRecordMemo();
        List<WalkingRecordWeatherState> validWeatherStates = Arrays.asList(WalkingRecordWeatherState.values());
        List<MultipartFile> newFiles = dto.getFiles();
        List<String> validExtensions = Arrays.asList("jpg", "png", "jpeg");

        WalkingRecordResponseDto data = null;

        if (!validWeatherStates.contains(walkingRecordWeatherState)) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_WALKING_RECORD_WEATHER_STATE);
        }

        if (walkingRecordDistance != null && walkingRecordDistance <= 0) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_WALKING_RECORD_DISTANCE);
        }

        if (walkingRecordWalkingTime == null || walkingRecordWalkingTime <= 0) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_WALKING_RECORD_TIME);
        }

        if (walkingRecordCreateAt != null && walkingRecordCreateAt.isAfter(LocalDate.now())) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_PET_BIRTH_DATE);
        }

        try {
            Optional<WalkingRecord> optionalWalkingRecord = walkingRecordRepository.findWalkingRecordBytPetIdAndWalkingRecordId(userId, petId, walkingRecordId);

            if (optionalWalkingRecord.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_WALKING_RECORD_ID);
            }

            WalkingRecord walkingRecord = optionalWalkingRecord.get();

            // 기존 첨부파일 삭제
            List<WalkingRecordAttachment> existingAttachments = walkingRecordAttachmentRepository.findByWRId(userId, petId, walkingRecordId);

            for (WalkingRecordAttachment attachment : existingAttachments) {
                fileService.removeFile((attachment.getWalkingRecordAttachmentFile()));
                walkingRecordAttachmentRepository.delete(attachment);
            }

            // 새로운 첨부파일 추가
            if (newFiles != null && !newFiles.isEmpty()) {
                for (MultipartFile file : newFiles) {
                    String filePath = fileService.uploadFile(file, "walking-record");
                    if (filePath != null) {
                        WalkingRecordAttachment attachment = new WalkingRecordAttachment();
                        attachment.setWalkingRecord(walkingRecord);
                        attachment.setWalkingRecordAttachmentFile(filePath);
                        walkingRecordAttachmentRepository.save(attachment);
                    }
               }
            }

            walkingRecord = walkingRecord.toBuilder()
                    .walkingRecordWeatherState(walkingRecordWeatherState != null ? walkingRecordWeatherState : WalkingRecordWeatherState.SUNNY)
                    .walkingRecordDistance(walkingRecordDistance != null ? walkingRecordDistance : walkingRecord.getWalkingRecordDistance())
                    .walkingRecordWalkingTime(walkingRecordWalkingTime != null ? walkingRecordWalkingTime : walkingRecord.getWalkingRecordWalkingTime())
                    .walkingRecordCreateAt(walkingRecordCreateAt != null ? walkingRecordCreateAt : walkingRecord.getWalkingRecordCreateAt())
                    .walkingRecordMemo(walkingRecordMemo != null ? walkingRecordMemo : walkingRecord.getWalkingRecordMemo())
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
    public ResponseDto<Void> deleteWalkingRecord(Long userId, Long petId, Long walkingRecordId) {
        try {
            Optional<WalkingRecord> optionalWalkingRecord = walkingRecordRepository.findWalkingRecordBytPetIdAndWalkingRecordId(userId, petId, walkingRecordId);

            if (optionalWalkingRecord.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_WALKING_RECORD_ID);
            }

            WalkingRecord walkingRecord = optionalWalkingRecord.get();
            walkingRecordRepository.delete(walkingRecord);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }
}