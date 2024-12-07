package com.korit.projectrrs.controller;

import com.korit.projectrrs.common.ApiMappingPattern;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.pet.request.PetRequestDto;
import com.korit.projectrrs.dto.pet.request.UpdatePetRequestDto;
import com.korit.projectrrs.dto.pet.response.PetListResponseDto;
import com.korit.projectrrs.dto.pet.response.PetResponseDto;
import com.korit.projectrrs.security.PrincipalUser;
import com.korit.projectrrs.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.PET)
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    private static final String PET_GET_BY_ID = "/{petProfileId}";
    private static final String PET_PUT = "/{petProfileId}";
    private static final String PET_DELETE = "/{petProfileId}";

    @PostMapping
    public ResponseEntity<ResponseDto<PetResponseDto>> createPetProfile(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestBody PetRequestDto dto
    ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<PetResponseDto> response = petService.createPetProfile(userId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<PetListResponseDto>>> getPetProfileList(
            @AuthenticationPrincipal PrincipalUser principalUser
    ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<List<PetListResponseDto>> response = petService.getPetProfileList(userId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(PET_GET_BY_ID)
    public ResponseEntity<ResponseDto<PetResponseDto>> getPetProfileInfo(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable long petProfileId
    ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<PetResponseDto> response = petService.getPetProfileInfo(userId, petProfileId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping(PET_PUT)
    public ResponseEntity<ResponseDto<PetResponseDto>> updatePetProfile(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable long petProfileId,
            @RequestBody UpdatePetRequestDto dto
    ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<PetResponseDto> response = petService.updatePetProfile(userId, petProfileId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping(PET_DELETE)
    public ResponseEntity<ResponseDto<Void>> deletePetProfile(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable long petProfileId
    ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<Void> response = petService.deletePetProfile(userId, petProfileId);
        HttpStatus status = response.isResult() ? HttpStatus.NO_CONTENT : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}
