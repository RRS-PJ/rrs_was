package com.korit.projectrrs.dto.pet.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@Data
public class UpdatePetRequestDto {
    private String petName;
    private Character petGender;
    private String petBirthDate;
    private Integer petWeight;
    private MultipartFile petImageUrl;
    private Character petNeutralityYn;
    private String petAddInfo;
}
