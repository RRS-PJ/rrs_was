package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.pet.request.PetRequestDto;
import com.korit.projectrrs.dto.pet.request.UpdatePetRequestDto;
import com.korit.projectrrs.dto.pet.response.PetListResponseDto;
import com.korit.projectrrs.dto.pet.response.PetResponseDto;
import java.util.List;

public interface PetService {
    ResponseDto<PetResponseDto> createPet(Long userId, PetRequestDto dto);
    ResponseDto<List<PetListResponseDto>> getPetList(Long userId);
    ResponseDto<PetResponseDto> getPetInfo(Long userId, Long petId);
    ResponseDto<PetResponseDto> updatePet(Long userId, Long petId, UpdatePetRequestDto dto);
    ResponseDto<Void> deletePet(Long userId, Long petId);
}
