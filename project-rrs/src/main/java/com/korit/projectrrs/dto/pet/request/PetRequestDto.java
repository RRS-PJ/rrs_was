package com.korit.projectrrs.dto.pet.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@Data
public class PetRequestDto {
    @NotBlank
    private String petName;
    @NotBlank
    private Character petGender;
    @NotBlank
    private String petBirthDate;
    @NotBlank
    private Integer petWeight;
    private MultipartFile petImageUrl;
    @NotBlank
    private Character petNeutralityYn;

    private String petAddInfo;
}
