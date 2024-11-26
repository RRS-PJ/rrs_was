package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.petProfile.request.PetProfileRequestDto;
import com.korit.projectrrs.dto.petProfile.request.UpdatePetProfileRequestDto;
import com.korit.projectrrs.dto.petProfile.response.PetProfileListResponseDto;
import com.korit.projectrrs.dto.petProfile.response.PetProfileResponseDto;

import java.util.List;

public interface PetProfileService {
    ResponseDto<PetProfileResponseDto> createPetProfile(String userId, PetProfileRequestDto dto);
    ResponseDto<List<PetProfileListResponseDto>> getPetProfileList(String userId);
    ResponseDto<PetProfileResponseDto> getPetProfileInfo(String userId, Long id);
    ResponseDto<PetProfileResponseDto> updatePetProfile(String userId, Long id, UpdatePetProfileRequestDto dto);
    ResponseDto<Void> deletePetProfile(String userId, Long id);
}
