package com.korit.projectrrs.dto.pet.response;

import com.korit.projectrrs.entity.Pet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetResponseDto {
    private String petName;
    private Character petGender;
    private String petBirthDate;
    private Integer petWeight;
    private String petImageUrl;
    private String petAddInfo;
    private Character petNeutralityYn;

    public PetResponseDto(Pet pet) {
        this.petName = pet.getPetName();
        this.petGender = pet.getPetGender();
        this.petBirthDate = pet.getPetBirthDate();
        this.petWeight = pet.getPetWeight();
        this.petImageUrl = pet.getPetImageUrl();
        this.petAddInfo = pet.getPetAddInfo();
        this.petNeutralityYn = pet.getPetNeutralityYn();
    }
}
