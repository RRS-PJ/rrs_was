package com.korit.projectrrs.dto.petProfile.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdatePetProfileRequestDto {
    private String petProfileName;
    private Character petProfileGender;
    private String petProfileBirthDate;
    private int petProfileWeight;
    private String petProfileImageUrl;
    private Character petProfileNeutralityYn;
    private String petProfileAddInfo;
}
