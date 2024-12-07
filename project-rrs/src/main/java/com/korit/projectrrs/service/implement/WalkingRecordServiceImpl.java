package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.common.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.walkingRecord.request.UpdateWalkingRecordRequestDto;
import com.korit.projectrrs.dto.walkingRecord.request.WalkingRecordRequestDto;
import com.korit.projectrrs.dto.walkingRecord.response.WalkingRecordListResponseDto;
import com.korit.projectrrs.dto.walkingRecord.response.WalkingRecordResponseDto;
import com.korit.projectrrs.dto.walkingRecordAttachment.response.WalkingRecordAttachmentResponseDto;
import com.korit.projectrrs.entity.*;
import com.korit.projectrrs.repositoiry.PetRepository;
import com.korit.projectrrs.repositoiry.WalkingRecordAttachmentRepository;
import com.korit.projectrrs.repositoiry.WalkingRecordRepository;
import com.korit.projectrrs.service.WalkingRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public ResponseDto<WalkingRecordResponseDto> createWalkingRecord(Long userId, Long petId, WalkingRecordRequestDto dto, List<MultipartFile> files) {
        WalkingRecordWeatherState walkingRecordWeatherState = dto.getWalkingRecordWeatherState() != null
                ? dto.getWalkingRecordWeatherState()
                : WalkingRecordWeatherState.SUNNY;
        Integer walkingRecordDistance = dto.getWalkingRecordDistance();
        Integer hours = dto.getWalkingRecordWalkingHours();
        Integer minutes = dto.getWalkingRecordWalkingMinutes();
        LocalDate walkingRecordCreateAt = dto.getWalkingRecordCreateAt();
        String walkingRecordMemo = dto.getWalkingRecordMemo();

        WalkingRecordResponseDto data = null;

        List<WalkingRecordWeatherState> validWeatherStates = Arrays.asList(WalkingRecordWeatherState.values());

        if (!validWeatherStates.contains(walkingRecordWeatherState)) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_WALKING_RECORD_WEATHER_STATE);
        }

        if (walkingRecordDistance == null || walkingRecordDistance <= 0) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_WALKING_RECORD_DISTANCE);
        }

        if (minutes == null || minutes < 0 || minutes >= 60) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_WALKING_RECORD_TIME);
        }

        int totalMinutes = (hours != null ? hours : 0) * 60 + minutes;

        if (walkingRecordCreateAt == null) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_WALKING_RECORD_CREATE_AT);
        }

        LocalDate now = LocalDate.now();
        if (walkingRecordCreateAt.isAfter(now)) {
            return ResponseDto.setFailed(ResponseMessage.TIME_IN_FUTURE_NOT_ALLOWED);
        }

        try {
            Optional<Pet> optionalPetProfile = petRepository.findPetByUserId(userId, petId);

            if (optionalPetProfile.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_PET_ID);
            }

            Pet pet = optionalPetProfile.get();

            WalkingRecord walkingRecord = WalkingRecord.builder()
                    .pet(pet)
                    .walkingRecordWeatherState(walkingRecordWeatherState != null ? walkingRecordWeatherState : WalkingRecordWeatherState.SUNNY)
                    .walkingRecordDistance(walkingRecordDistance)
                    .walkingRecordWalkingTime(totalMinutes)
                    .walkingRecordCreateAt(walkingRecordCreateAt)
                    .walkingRecordMemo(walkingRecordMemo)
                    .build();

            walkingRecordRepository.save(walkingRecord);

            if (files != null && !files.isEmpty()) {
                List<WalkingRecordAttachmentResponseDto> attachmentResponseList = new ArrayList<>();

                for (MultipartFile file : files) {
                    String originalFileName = file.getOriginalFilename();
                    System.out.println("Original FileName: " + originalFileName);

                    if (originalFileName != null && !originalFileName.isEmpty()) {
                        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1).toLowerCase();

                        if (!fileExtension.equals("png") && !fileExtension.equals("jpg")) {
                            return ResponseDto.setFailed(ResponseMessage.INVALID_FILE);
                        }

                        String sanitizedFileName = originalFileName.replaceAll("[\\x00-\\x1F\\x7F]", "_");
                        String fileName = System.currentTimeMillis() + "_" + sanitizedFileName;

                        Path path = Paths.get(uploadDir, fileName);
                        Files.copy(file.getInputStream(), path);

                        WalkingRecordAttachment walkingRecordAttachment = WalkingRecordAttachment.builder()
                                .walkingRecordId(walkingRecord.getWalkingRecordId())
                                .walkingRecordAttachmentFile(path.toString())
                                .build();

                        walkingRecordAttachmentRepository.save(walkingRecordAttachment);

                        WalkingRecordAttachmentResponseDto attachmentResponseDto = WalkingRecordAttachmentResponseDto.builder()
                                .fileName(Paths.get(walkingRecordAttachment.getWalkingRecordAttachmentFile()).getFileName().toString())
                                .fileUrl(walkingRecordAttachment.getWalkingRecordAttachmentFile())
                                .build();

                        attachmentResponseList.add(attachmentResponseDto);
                    }
                }

                data.setAttachments(attachmentResponseList);
            }

//            if (attachmentIdsToDelete != null && !attachmentIdsToDelete.isEmpty()) {
//                for (Long attachmentId : attachmentIdsToDelete) {
//                    Optional<WalkingRecordAttachment> attachment = walkingRecordAttachmentRepository.findById(attachmentId);
//                    attachment.ifPresent(att -> walkingRecordAttachmentRepository.delete(att));
//                }
//            }
//
//            data.setWalkingRecord(new WalkingRecordResponseDto(walkingRecord));
//            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

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
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_WALKING_RECORD_ID);
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
    public ResponseDto<WalkingRecordResponseDto> getWalkingRecord(Long userId, Long petId, Long walkingRecordId) {
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
    public ResponseDto<WalkingRecordResponseDto> updateWalkingRecord(Long userId, Long petId, Long walkingRecordId, UpdateWalkingRecordRequestDto dto) {
        WalkingRecordWeatherState walkingRecordWeatherState = dto.getWalkingRecordWeatherState();
        Integer walkingRecordDistance = dto.getWalkingRecordDistance();
        Integer hours = dto.getWalkingRecordWalkingHours();
        Integer minutes = dto.getWalkingRecordWalkingMinutes();
        LocalDate walkingRecordCreateAt = dto.getWalkingRecordCreateAt();
        String walkingRecordMemo = dto.getWalkingRecordMemo();
        List<WalkingRecordAttachment> walkingRecordAttachments = dto.getWalkingRecordAttachments();

        WalkingRecordResponseDto data = null;

        if (walkingRecordDistance != null && walkingRecordDistance <= 0) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_WALKING_RECORD_DISTANCE);
        }

        if (minutes != null && minutes < 0 || minutes >= 60) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_WALKING_RECORD_TIME);
        }

        int totalMinutes = (hours != null ? hours : 0) * 60 + minutes;

        if (walkingRecordCreateAt != null && walkingRecordCreateAt.isAfter(LocalDate.now())) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_PET_BIRTH_DATE);
        }

        try {
            Optional<WalkingRecord> optionalWalkingRecord = walkingRecordRepository.findWalkingRecordBytPetIdAndWalkingRecordId(userId, petId, walkingRecordId);

            if (optionalWalkingRecord.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_WALKING_RECORD_ID);
            }

            WalkingRecord walkingRecord = optionalWalkingRecord.get();

            System.out.println("Received Weather State: " + walkingRecordWeatherState);

            walkingRecord = walkingRecord.toBuilder()
                    .walkingRecordWeatherState(walkingRecordWeatherState != null ? walkingRecordWeatherState : WalkingRecordWeatherState.SUNNY)
                    .walkingRecordDistance(walkingRecordDistance != null ? walkingRecordDistance : walkingRecord.getWalkingRecordDistance())
                    .walkingRecordWalkingTime(totalMinutes)
                    .walkingRecordCreateAt(walkingRecordCreateAt != null ? walkingRecordCreateAt : walkingRecord.getWalkingRecordCreateAt())
                    .walkingRecordMemo(walkingRecordMemo != null ? walkingRecordMemo : walkingRecord.getWalkingRecordMemo())
                    .walkingRecordAttachments(walkingRecordAttachments != null ? walkingRecordAttachments :walkingRecord.getWalkingRecordAttachments())
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
