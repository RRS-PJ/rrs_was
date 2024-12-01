package com.korit.projectrrs.controller;

import com.korit.projectrrs.common.ApiMappingPattern;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.petProfile.request.PetProfileRequestDto;
import com.korit.projectrrs.dto.petProfile.request.UpdatePetProfileRequestDto;
import com.korit.projectrrs.dto.petProfile.response.PetProfileListResponseDto;
import com.korit.projectrrs.dto.petProfile.response.PetProfileResponseDto;
import com.korit.projectrrs.service.PetProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.PET)
@RequiredArgsConstructor
public class PetProfileController {

    private final PetProfileService petProfileService;

    private static final String PET_GET_BY_ID = "/{petProfileId}";
    private static final String PET_PUT = "/{petProfileId}";
    private static final String PET_DELETE = "/{petProfileId}";

    @PostMapping
    public ResponseEntity<ResponseDto<PetProfileResponseDto>> createPetProfile(
            @AuthenticationPrincipal String userId,
            @RequestBody PetProfileRequestDto dto
    ) {
        ResponseDto<PetProfileResponseDto> response = petProfileService.createPetProfile(userId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<PetProfileListResponseDto>>> getPetProfileList(
            @AuthenticationPrincipal String userId
    ) {
        ResponseDto<List<PetProfileListResponseDto>> response = petProfileService.getPetProfileList(userId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(PET_GET_BY_ID)
    public ResponseEntity<ResponseDto<PetProfileResponseDto>> getPetProfileInfo(
            @AuthenticationPrincipal String userId,
            @PathVariable long petProfileId
    ) {
        ResponseDto<PetProfileResponseDto> response = petProfileService.getPetProfileInfo(userId, petProfileId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping(PET_PUT)
    public ResponseEntity<ResponseDto<PetProfileResponseDto>> updatePetProfile(
            @AuthenticationPrincipal String userId,
            @PathVariable long petProfileId,
            @RequestBody UpdatePetProfileRequestDto dto
    ) {
        ResponseDto<PetProfileResponseDto> response = petProfileService.updatePetProfile(userId, petProfileId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping(PET_DELETE)
    public ResponseEntity<ResponseDto<Void>> deletePetProfile(
            @AuthenticationPrincipal String userId,
            @PathVariable long petProfileId
    ) {
        ResponseDto<Void> response = petProfileService.deletePetProfile(userId, petProfileId);
        HttpStatus status = response.isResult() ? HttpStatus.NO_CONTENT : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}
