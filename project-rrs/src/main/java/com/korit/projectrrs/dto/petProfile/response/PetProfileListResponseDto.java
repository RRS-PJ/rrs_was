package com.korit.projectrrs.dto.petProfile.response;

import com.korit.projectrrs.entity.PetProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetProfileListResponseDto {
    private String petProfileName;
    private String petProfileImageUrl;

    public PetProfileListResponseDto(PetProfile petProfile) {
        this.petProfileName = petProfile.getPetProfileName();
        this.petProfileImageUrl = petProfile.getPetProfileImageUrl();
    }
}
