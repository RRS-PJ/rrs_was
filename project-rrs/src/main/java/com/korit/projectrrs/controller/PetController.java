package com.korit.projectrrs.controller;

import com.korit.projectrrs.common.constant.ApiMappingPattern;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.pet.request.PetRequestDto;
import com.korit.projectrrs.dto.pet.request.UpdatePetRequestDto;
import com.korit.projectrrs.dto.pet.response.PetListResponseDto;
import com.korit.projectrrs.dto.pet.response.PetResponseDto;
import com.korit.projectrrs.security.PrincipalUser;
import com.korit.projectrrs.service.PetService;
import jakarta.validation.Valid;
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

    private static final String PET_GET_BY_ID = "/{petId}";
    private static final String PET_PUT = "/{petId}";
    private static final String PET_DELETE = "/{petId}";

    @PostMapping
    public ResponseEntity<ResponseDto<PetResponseDto>> createPet(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @Valid @ModelAttribute PetRequestDto dto
    ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<PetResponseDto> response = petService.createPet(userId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<PetListResponseDto>>> getPetList(
            @AuthenticationPrincipal PrincipalUser principalUser
    ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<List<PetListResponseDto>> response = petService.getPetList(userId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(PET_GET_BY_ID)
    public ResponseEntity<ResponseDto<PetResponseDto>> getPetInfo(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long petId
    ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<PetResponseDto> response = petService.getPetInfo(userId, petId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping(PET_PUT)
    public ResponseEntity<ResponseDto<PetResponseDto>> updatePet(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long petId,
            @Valid @ModelAttribute UpdatePetRequestDto dto
    ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<PetResponseDto> response = petService.updatePet(userId, petId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping(PET_DELETE)
    public ResponseEntity<ResponseDto<Void>> deletePet(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long petId
    ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<Void> response = petService.deletePet(userId, petId);
        HttpStatus status = response.isResult() ? HttpStatus.NO_CONTENT : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}