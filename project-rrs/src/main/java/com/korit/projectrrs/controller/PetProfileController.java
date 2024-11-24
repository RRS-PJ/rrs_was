package com.korit.projectrrs.controller;

import com.korit.projectrrs.common.ApiMappingPattern;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.pet.request.PetProfileRequestDto;
import com.korit.projectrrs.dto.pet.request.UpdatePetProfileRequestDto;
import com.korit.projectrrs.dto.pet.response.PetProfileListResponseDto;
import com.korit.projectrrs.dto.pet.response.PetProfileResponseDto;
import com.korit.projectrrs.service.PetProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiMappingPattern.PET)
@RequiredArgsConstructor
public class PetProfileController {

    private final PetProfileService petProfileService;

    private static final String PET_PUT = "/{id}";
    private static final String PET_GET_BY_ID = "/{id}";
    private static final String PET_DELETE = "/{id}";

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
    public ResponseEntity<ResponseDto<PetProfileListResponseDto>> getPetProfileList(
            @AuthenticationPrincipal String userId
    ) {
        ResponseDto<PetProfileListResponseDto> response = petProfileService.getPetProfileList(userId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(PET_GET_BY_ID)
    public ResponseEntity<ResponseDto<PetProfileResponseDto>> getPetProfileInfo(
            @AuthenticationPrincipal String userId,
            @PathVariable Long id
    ) {
        ResponseDto<PetProfileResponseDto> response = petProfileService.getPetProfileInfo(userId, id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping(PET_PUT)
    public ResponseEntity<ResponseDto<PetProfileResponseDto>> updatePetProfile(
            @AuthenticationPrincipal String userId,
            @PathVariable Long id,
            @RequestBody UpdatePetProfileRequestDto dto
    ) {
        ResponseDto<PetProfileResponseDto> response = petProfileService.updatePetProfile(userId, id, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping(PET_DELETE)
    public ResponseEntity<ResponseDto<Void>> deletePetProfile(
            @AuthenticationPrincipal String userId,
            @PathVariable Long id
    ) {
        ResponseDto<Void> response = petProfileService.deletePetProfile(userId, id);
        HttpStatus status = response.isResult() ? HttpStatus.NO_CONTENT : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}
