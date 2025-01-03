package com.korit.projectrrs.controller;

import com.korit.projectrrs.common.ApiMappingPattern;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.healthrecord.request.HealthRecordCreateRequestDto;
import com.korit.projectrrs.dto.healthrecord.request.HealthRecordUpdateRequestDto;
import com.korit.projectrrs.dto.healthrecord.response.HealthRecordAllResponseDto;
import com.korit.projectrrs.dto.healthrecord.response.HealthRecordResponseDto;
import com.korit.projectrrs.security.PrincipalUser;
import com.korit.projectrrs.service.HealthRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.korit.projectrrs.common.ApiMappingPattern.*;

@RestController
@RequestMapping(ApiMappingPattern.HEALTH_RECORDS)
@RequiredArgsConstructor
public class HealthRecordController {

    private final HealthRecordService healthRecordService;

    @PostMapping(HEALTH_RECORD_CREATE)
    public ResponseEntity<ResponseDto<HealthRecordResponseDto>> createHealthRecord(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long petId,
            @ModelAttribute  @Valid HealthRecordCreateRequestDto requestDto
    ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<HealthRecordResponseDto> response = healthRecordService.createHealthRecord(userId, petId, requestDto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping(HEALTH_RECORD_UPDATE)
    public ResponseEntity<ResponseDto<HealthRecordResponseDto>> updateHealthRecord(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long petId,
            @PathVariable Long healthRecordId,
            @ModelAttribute  @Valid HealthRecordUpdateRequestDto requestDto
    ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<HealthRecordResponseDto> response = healthRecordService.updateHealthRecord(userId, petId, healthRecordId, requestDto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(HEALTH_RECORD_GET_BY_ID)
    public ResponseEntity<ResponseDto<HealthRecordResponseDto>> getHealthRecord(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long petId,
            @PathVariable Long healthRecordId
    ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<HealthRecordResponseDto> response = healthRecordService.getHealthRecord(userId, petId, healthRecordId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(HEALTH_RECORD_GET_LIST)
    public ResponseEntity<ResponseDto<List<HealthRecordAllResponseDto>>> getAllHealthRecords(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long petId
    ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<List<HealthRecordAllResponseDto>> response = healthRecordService.getAllHealthRecords(userId, petId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping(HEALTH_RECORD_DELETE)
    public ResponseEntity<ResponseDto<Void>> deleteHealthRecord(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long petId,
            @PathVariable Long healthRecordId
    ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<Void> response = healthRecordService.deleteHealthRecord(userId, petId, healthRecordId);
        HttpStatus status = response.isResult() ? HttpStatus.NO_CONTENT : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}
