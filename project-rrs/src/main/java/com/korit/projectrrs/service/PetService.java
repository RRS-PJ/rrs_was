package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.pet.request.PetRequestDto;
import com.korit.projectrrs.dto.pet.request.UpdatePetRequestDto;
import com.korit.projectrrs.dto.pet.response.PetListResponseDto;
import com.korit.projectrrs.dto.pet.response.PetResponseDto;
import com.korit.projectrrs.security.PrincipalUser;

import java.util.List;

public interface PetService {
    ResponseDto<PetResponseDto> createPetProfile(Long userId, PetRequestDto dto);
    ResponseDto<List<PetListResponseDto>> getPetProfileList(Long userId);
    ResponseDto<PetResponseDto> getPetProfileInfo(Long userId, Long petId);
    ResponseDto<PetResponseDto> updatePetProfile(Long userId, Long petId, UpdatePetRequestDto dto);
    ResponseDto<Void> deletePetProfile(Long userId, Long petId);
}
