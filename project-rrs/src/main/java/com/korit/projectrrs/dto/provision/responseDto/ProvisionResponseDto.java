package com.korit.projectrrs.dto.provision.responseDto;

import com.korit.projectrrs.entity.Pet;
import com.korit.projectrrs.entity.Reservation;
import com.korit.projectrrs.entity.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvisionResponseDto {
    private Long reservationId;
    private LocalDate reservationStartDate;
    private LocalDate reservationEndDate;

    private Long petId;
    private String petName;
    private Character petGender;
    private String petBirthDate;
    private Integer petWeight;
    private String petImageUrl;
    private String petAddInfo;
    private Character petNeutralityYn;

    private Long userId;
    private String nickName;
    private String address;
    private String phone;

    private String reservationMemo;
    private ReservationStatus reservationStatus;

    public ProvisionResponseDto(Reservation reservation) {
        this.reservationId = reservation.getReservationId();
        this.reservationStartDate = reservation.getReservationStartDate();
        this.reservationEndDate = reservation.getReservationEndDate();

        if (!reservation.getUser().getPet().isEmpty()) {
            Pet pet = reservation.getUser().getPet().get(0); // 첫 번째 펫을 가져옴
            this.petId = pet.getPetId();
            this.petName = pet.getPetName();
            this.petGender = pet.getPetGender();
            this.petBirthDate = pet.getPetBirthDate();
            this.petWeight = pet.getPetWeight();
            this.petImageUrl = pet.getPetImageUrl();
            this.petAddInfo = pet.getPetAddInfo();
            this.petNeutralityYn = pet.getPetNeutralityYn();
        }
        this.userId = reservation.getUser().getUserId();
        this.nickName = reservation.getUser().getNickname();
        this.address = reservation.getUser().getAddress();
        this.phone = reservation.getUser().getPhone();
        this.reservationMemo = reservation.getReservationMemo();
        this.reservationStatus = reservation.getReservationStatus();
    }
}
