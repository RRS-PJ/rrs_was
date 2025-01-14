package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.common.constant.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.pet.request.PetRequestDto;
import com.korit.projectrrs.dto.pet.request.UpdatePetRequestDto;
import com.korit.projectrrs.dto.pet.response.PetListResponseDto;
import com.korit.projectrrs.dto.pet.response.PetResponseDto;
import com.korit.projectrrs.entity.Pet;
import com.korit.projectrrs.entity.User;
import com.korit.projectrrs.repositoiry.PetRepository;
import com.korit.projectrrs.repositoiry.UserRepository;
import com.korit.projectrrs.service.FileService;
import com.korit.projectrrs.service.PetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final UserRepository userRepository;
    private final FileService fileService;

    @Override
    public ResponseDto<PetResponseDto> createPet(Long userId, @Valid PetRequestDto dto) {
        String petName = dto.getPetName();
        Character petGender = dto.getPetGender();
        String petBirthDate = dto.getPetBirthDate();
        Integer petWeight = dto.getPetWeight();
        MultipartFile petImageUrl = dto.getPetImageUrl();
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
                !petImageUrl.getOriginalFilename().matches("(?i).*\\.(jpg|png|jpeg)$")) {
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

            String uploadedFileName = null;
            if (petImageUrl != null && !petImageUrl.isEmpty()) {
                uploadedFileName = fileService.uploadFile(petImageUrl, "pet-profileImage");
            }

            if (uploadedFileName == null) {
                uploadedFileName = "/static/images/pet-default-profile.jpg";  // 기본 이미지 파일 경로
            }

            Pet pet = Pet.builder()
                    .petName(petName)
                    .petGender(petGender)
                    .petBirthDate(petBirthDate)
                    .petWeight(petWeight)
                    .petNeutralityYn(petNeutralityYn)
                    .petAddInfo(petAddInfo)
                    .petImageUrl(uploadedFileName)
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
        MultipartFile petImage = dto.getPetImageUrl();
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

        if (petImage != null && !petImage.isEmpty() &&
                !petImage.getOriginalFilename().matches("(?i).*\\.(jpg|png|jpeg)$")) {
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

            boolean isSame = (petName == null || petName.equals(pet.getPetName())) &&
                    (petGender == null || petGender.equals(pet.getPetGender())) &&
                    (petBirthDate == null || petBirthDate.equals(pet.getPetBirthDate())) &&
                    (petWeight == null || petWeight.equals(pet.getPetWeight())) &&
                    (petNeutralityYn == null || petNeutralityYn.equals(pet.getPetNeutralityYn()) &&
                    (petAddInfo == null || petAddInfo.equals(pet.getPetAddInfo())) &&
                    (petImage == null || petImage.getOriginalFilename().equals(pet.getPetImageUrl())));

            if (isSame) {
                return ResponseDto.setFailed(ResponseMessage.NO_MODIFIED_VALUES);
            }

            String petImageUrl = pet.getPetImageUrl();
            String defaultProfileImageUrl = "images/pet-default-profile.png";

            if (petImage != null && !petImage.isEmpty()) {
                String filePath = fileService.uploadFile(petImage, "pet-profileImage");
                if (filePath != null) {
                    petImageUrl = filePath;
                }
            } else if (petImageUrl == null || petImageUrl.isEmpty()) {
                petImageUrl = defaultProfileImageUrl;
            }

            pet = pet.toBuilder()
                    .petName(petName != null ? petName : pet.getPetName())
                    .petGender(petGender != null ? petGender : pet.getPetGender())
                    .petBirthDate(petBirthDate != null ? petBirthDate : pet.getPetBirthDate())
                    .petWeight(petWeight != null ? petWeight : pet.getPetWeight())
                    .petNeutralityYn(petNeutralityYn != null ? petNeutralityYn : pet.getPetNeutralityYn())
                    .petAddInfo(petAddInfo != null ? petAddInfo : pet.getPetAddInfo())
                    .petImageUrl(petImageUrl)
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