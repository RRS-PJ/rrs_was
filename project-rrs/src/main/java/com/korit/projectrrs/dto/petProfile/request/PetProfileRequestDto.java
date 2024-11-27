package com.korit.projectrrs.dto.petProfile.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PetProfileRequestDto {
    @NotBlank
    private String petProfileName;
    @NotBlank
    private Character petProfileGender;
    @NotBlank
    private String petProfileBirthDate;
    @NotBlank
    private Integer petProfileWeight;
    @NotBlank
    private String petProfileImageUrl;
    @NotBlank
    private Character petProfileNeutralityYn;

    private String petProfileAddInfo;
}
