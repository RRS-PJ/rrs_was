package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.common.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.petProfile.request.PetProfileRequestDto;
import com.korit.projectrrs.dto.petProfile.request.UpdatePetProfileRequestDto;
import com.korit.projectrrs.dto.petProfile.response.PetProfileListResponseDto;
import com.korit.projectrrs.dto.petProfile.response.PetProfileResponseDto;
import com.korit.projectrrs.entity.PetProfile;
import com.korit.projectrrs.entity.User;
import com.korit.projectrrs.repositoiry.PetProfileRepository;
import com.korit.projectrrs.repositoiry.UserRepository;
import com.korit.projectrrs.service.PetProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetProfileServiceImpl implements PetProfileService {

    private final PetProfileRepository petProfileRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseDto<PetProfileResponseDto> createPetProfile(String userId, @Valid PetProfileRequestDto dto) {
        String petProfileName = dto.getPetProfileName();
        Character petProfileGender = dto.getPetProfileGender();
        String petProfileBirthDate = dto.getPetProfileBirthDate();
        Integer petProfileWeight = dto.getPetProfileWeight();
        String petProfileImageUrl = dto.getPetProfileImageUrl();
        Character petProfileNeutralityYn = dto.getPetProfileNeutralityYn();
        String petProfileAddInfo = dto.getPetProfileAddInfo();

        PetProfileResponseDto data = null;

        if (petProfileName == null || petProfileName.isEmpty() || !petProfileName.matches("^[가-힣]+$")) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_PET_NAME);
        }

        if (petProfileGender == null || (petProfileGender != '0' && petProfileGender != '1')) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_PET_GENDER);
        }

        if (petProfileBirthDate == null || petProfileBirthDate.isEmpty() || !petProfileBirthDate.matches("^[0-9]{6}$")) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_PET_BIRTH_DATE);
        }

        if (petProfileWeight == null || petProfileWeight <= 0) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_PET_WEIGHT);
        }

        if (petProfileImageUrl != null && !petProfileImageUrl.isEmpty() &&
                !petProfileImageUrl.matches(".*\\.(jpg|png)$")) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_PET_PROFILE);
        }

        if (petProfileNeutralityYn == null || (petProfileNeutralityYn != '0' && petProfileNeutralityYn != '1')) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_PET_NEUTRALITY_YN);
        }

        try {
            Optional<User> optionalUser = userRepository.findByUserId(userId);

            if (optionalUser.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);
            }

            User user = optionalUser.get();

            PetProfile petProfile = PetProfile.builder()
                    .petProfileName(petProfileName)
                    .petProfileGender(petProfileGender)
                    .petProfileBirthDate(petProfileBirthDate)
                    .petProfileWeight(petProfileWeight)
                    .petProfileNeutralityYn(petProfileNeutralityYn)
                    .petProfileAddInfo(petProfileAddInfo)
                    .petProfileImageUrl(petProfileImageUrl != null ? petProfileImageUrl : "petExample.jpg")
                    .user(user)
                    .build();

            petProfileRepository.save(petProfile);

            data = new PetProfileResponseDto(petProfile);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
        }

    @Override
    public ResponseDto<PetProfileListResponseDto> getPetProfileList(String userId) {
        return null;
    }

    @Override
    public ResponseDto<PetProfileResponseDto> getPetProfileInfo(String userId, Long petProfileId) {
        return null;
    }

    @Override
    public ResponseDto<PetProfileResponseDto> updatePetProfile(String userId, Long petProfileId, @Valid UpdatePetProfileRequestDto dto) {
        String petProfileName = dto.getPetProfileName();
        Character petProfileGender = dto.getPetProfileGender();
        String petProfileBirthDate = dto.getPetProfileBirthDate();
        Integer petProfileWeight = dto.getPetProfileWeight();
        String petProfileImageUrl = dto.getPetProfileImageUrl();
        Character petProfileNeutralityYn = dto.getPetProfileNeutralityYn();
        String petProfileAddInfo = dto.getPetProfileAddInfo();

        PetProfileResponseDto data = null;

        if (petProfileName != null && !petProfileName.isEmpty() && !petProfileName.matches("^[가-힣]+$")) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_USER_NAME);
        }

        if (petProfileGender != null && !petProfileGender.equals('0') && !petProfileGender.equals('1')) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_PET_GENDER);
        }

        if (petProfileBirthDate != null && petProfileBirthDate.isEmpty() && !petProfileBirthDate.matches("^[0-9]{6}$")) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_PET_BIRTH_DATE);
        }

        if (petProfileWeight != null && petProfileWeight <= 0) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_PET_WEIGHT);
        }

        if (petProfileImageUrl != null && !petProfileImageUrl.isEmpty() &&
                !petProfileImageUrl.matches(".*\\.(jpg|png)$")) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_PET_PROFILE);
        }

        if (petProfileNeutralityYn != null && (petProfileNeutralityYn != '0' && petProfileNeutralityYn != '1')) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_PET_NEUTRALITY_YN);
        }

        try {
            Optional<PetProfile> optionalPetProfile = petProfileRepository.findByUserIdAndPetProfileId(userId, petProfileId);

            if (optionalPetProfile.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_PET_ID);
            }

            PetProfile petProfile = optionalPetProfile.get();

            petProfile = petProfile.toBuilder()
                    .petProfileName(petProfileName != null ? petProfileName : petProfile.getPetProfileName())
                    .petProfileGender(petProfileGender != null ? petProfileGender : petProfile.getPetProfileGender())
                    .petProfileBirthDate(petProfileBirthDate != null ? petProfileBirthDate : petProfile.getPetProfileBirthDate())
                    .petProfileWeight(petProfileWeight != null ? petProfileWeight : petProfile.getPetProfileWeight())
                    .petProfileImageUrl(
                            (petProfileImageUrl != null && !petProfileImageUrl.isEmpty())
                                    ? petProfileImageUrl
                                    : (petProfileImageUrl == null && petProfile.getPetProfileImageUrl() != null)
                                    ? petProfile.getPetProfileImageUrl()
                                    : "petExample.jpg"
                    )
                    .petProfileNeutralityYn(petProfileNeutralityYn != null ? petProfileNeutralityYn : petProfile.getPetProfileNeutralityYn())
                    .petProfileAddInfo(petProfileAddInfo != null ? petProfileAddInfo : petProfile.getPetProfileAddInfo())
                    .build();

            petProfileRepository.save(petProfile);

            data = new PetProfileResponseDto(petProfile);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<Void> deletePetProfile(String userId, Long petProfileId) {
        return null;
    }
}
// 수정확인용