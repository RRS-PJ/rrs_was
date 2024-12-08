package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.common.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.pet.request.PetRequestDto;
import com.korit.projectrrs.dto.pet.request.UpdatePetRequestDto;
import com.korit.projectrrs.dto.pet.response.PetListResponseDto;
import com.korit.projectrrs.dto.pet.response.PetResponseDto;
import com.korit.projectrrs.entity.Pet;
import com.korit.projectrrs.entity.User;
import com.korit.projectrrs.repositoiry.PetRepository;
import com.korit.projectrrs.repositoiry.UserRepository;
import com.korit.projectrrs.security.PrincipalUser;
import com.korit.projectrrs.service.PetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseDto<PetResponseDto> createPet(Long userId, @Valid PetRequestDto dto) {
        String petName = dto.getPetName();
        Character petGender = dto.getPetGender();
        String petBirthDate = dto.getPetBirthDate();
        Integer petWeight = dto.getPetWeight();
        String petImageUrl = dto.getPetImageUrl();
        Character petNeutralityYn = dto.getPetNeutralityYn();
        String petAddInfo = dto.getPetAddInfo();

        PetResponseDto data = null;

        if (petName == null || petName.isEmpty() || !petName.matches("^[가-힣]+$")) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_PET_NAME);
        }

        if (petGender == null || (petGender != '0' && petGender != '1')) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_PET_GENDER);
        }

        if (petBirthDate == null || petBirthDate.isEmpty() || !petBirthDate.matches("^[0-9]{6}$")) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_PET_BIRTH_DATE);
        }

        if (petWeight == null || petWeight <= 0) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_PET_WEIGHT);
        }

        if (petImageUrl != null && !petImageUrl.isEmpty() &&
                !petImageUrl.matches("^(https?://.*\\.(jpg|png))$")) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_PET_PROFILE);
        }

        if (petNeutralityYn == null || (petNeutralityYn != '0' && petNeutralityYn != '1')) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_PET_NEUTRALITY_YN);
        }

        try {
            Optional<User> optionalUser = userRepository.findById(userId);

            if (optionalUser.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);
            }

            User user = optionalUser.get();

            Pet pet = Pet.builder()
                    .petName(petName)
                    .petGender(petGender)
                    .petBirthDate(petBirthDate)
                    .petWeight(petWeight)
                    .petNeutralityYn(petNeutralityYn)
                    .petAddInfo(petAddInfo)
                    .petImageUrl(petImageUrl != null ? petImageUrl : "petExample.jpg")
                    .user(user)
                    .build();

            petRepository.save(pet);

            data = new PetResponseDto(pet);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<List<PetListResponseDto>> getPetList(Long userId) {
        List<PetListResponseDto> data = new ArrayList<>();

        try {
            List<Pet> pets = petRepository.findAllPetByUserId(userId);

            if (pets.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_PET_ID);
            }

            data = pets.stream()
                    .map(PetListResponseDto::new)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<PetResponseDto> getPetInfo(Long userId, Long petId) {
        PetResponseDto data = null;

        try {
            Optional<Pet> optionalPet = petRepository.findPetByUserId(userId, petId);

            if (optionalPet.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_PET_ID);
            }

            Pet pet = optionalPet.get();

            data = new PetResponseDto(pet);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<PetResponseDto> updatePet(Long userId, Long petId, @Valid UpdatePetRequestDto dto) {
        String petName = dto.getPetName();
        Character petGender = dto.getPetGender();
        String petBirthDate = dto.getPetBirthDate();
        Integer petWeight = dto.getPetWeight();
        String petImageUrl = dto.getPetImageUrl();
        Character petNeutralityYn = dto.getPetNeutralityYn();
        String petAddInfo = dto.getPetAddInfo();

        PetResponseDto data = null;

        if (petName != null && !petName.isEmpty() && !petName.matches("^[가-힣]+$")) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_USER_NAME);
        }

        if (petGender != null && !petGender.equals('0') && !petGender.equals('1')) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_PET_GENDER);
        }

        if (petBirthDate != null && petBirthDate.isEmpty() && !petBirthDate.matches("^[0-9]{6}$")) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_PET_BIRTH_DATE);
        }

        if (petWeight != null && petWeight <= 0) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_PET_WEIGHT);
        }

        if (petImageUrl != null && !petImageUrl.isEmpty() &&
                !petImageUrl.matches("^(https?://.*\\.(jpg|png))$")) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_PET_PROFILE);
        }

        if (petNeutralityYn != null && (petNeutralityYn != '0' && petNeutralityYn != '1')) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_PET_NEUTRALITY_YN);
        }

        try {
            Optional<Pet> optionalPet = petRepository.findPetByUserId(userId, petId);

            if (optionalPet.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_PET_ID);
            }

            Pet pet = optionalPet.get();

            pet = pet.toBuilder()
                    .petName(petName != null ? petName : pet.getPetName())
                    .petGender(petGender != null ? petGender : pet.getPetGender())
                    .petBirthDate(petBirthDate != null ? petBirthDate : pet.getPetBirthDate())
                    .petWeight(petWeight != null ? petWeight : pet.getPetWeight())
                    .petImageUrl(
                            (petImageUrl != null && !petImageUrl.isEmpty())
                                    ? petImageUrl
                                    : (petImageUrl == null && pet.getPetImageUrl() != null)
                                    ? pet.getPetImageUrl()
                                    : "petExample.jpg"
                    )
                    .petNeutralityYn(petNeutralityYn != null ? petNeutralityYn : pet.getPetNeutralityYn())
                    .petAddInfo(petAddInfo != null ? petAddInfo : pet.getPetAddInfo())
                    .build();

            petRepository.save(pet);

            data = new PetResponseDto(pet);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<Void> deletePet(Long userId, Long petId) {
        try {
            Optional<Pet> optionalPet = petRepository.findPetByUserId(userId, petId);

            if (optionalPet.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_PET_ID);
            }

            Pet pet = optionalPet.get();
            petRepository.delete(pet);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }
}