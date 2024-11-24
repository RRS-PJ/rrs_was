package com.korit.projectrrs.dto.petProfile.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PetProfileRequestDto {
    @NotBlank
    private String petProfileName;
    @NotBlank
    private Character petProfileGender;
    @NotBlank
    private String petProfileBirthDate;
    @NotBlank
    private int petProfileWeight;
    @NotBlank
    private String petProfileImageUrl;
    @NotBlank
    private Character petProfileNeutralityYn;

    private String petProfileAddInfo;
}
