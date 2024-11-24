package com.korit.projectrrs.dto.pet.response;

import com.korit.projectrrs.entity.PetProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetProfileResponseDto {
    private String petProfileName;
    private Character petProfileGender;
    private String petProfileBirthDate;
    private int petProfileWeight;
    private String petProfileImageUrl;
    private String petProfileAddInfo;
    private Character petProfileNeutralityYn;

    public PetProfileResponseDto(PetProfile petProfile) {
        this.petProfileName = petProfile.getPetProfileName();
        this.petProfileGender = petProfile.getPetProfileGender();
        this.petProfileBirthDate = petProfile.getPetProfileBirthDate();
        this.petProfileWeight = petProfile.getPetProfileWeight();
        this.petProfileImageUrl = petProfile.getPetProfileImageUrl();
        this.petProfileAddInfo = petProfile.getPetProfileAddInfo();
        this.petProfileNeutralityYn = petProfile.getPetProfileNeutralityYn();
    }
}
