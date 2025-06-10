
package com.korit.projectrrs.dto.pet.response;

import com.korit.projectrrs.entity.Pet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetReservationDto {
    private Long petId;
    private String petName;
    private String petImageUrl;

    public PetReservationDto(Pet pet) {
        this.petId = pet.getPetId();
        this.petName = pet.getPetName();
        this.petImageUrl = pet.getPetImageUrl();
    }
}
