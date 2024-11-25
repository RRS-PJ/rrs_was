package com.korit.projectrrs.dto.petProfile.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UpdatePetProfileRequestDto {
    private String petProfileName;
    private Character petProfileGender;
    private String petProfileBirthDate;
    private Integer petProfileWeight;
    private String petProfileImageUrl;
    private Character petProfileNeutralityYn;
    private String petProfileAddInfo;
}
