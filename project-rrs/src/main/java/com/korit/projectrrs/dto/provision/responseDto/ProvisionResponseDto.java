package com.korit.projectrrs.dto.provision.responseDto;

import com.korit.projectrrs.dto.pet.response.PetResponseDto;
import com.korit.projectrrs.entity.Pet;
import com.korit.projectrrs.entity.Reservation;
import com.korit.projectrrs.entity.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvisionResponseDto {
    private Long reservationId;
    private LocalDate reservationStartDate;
    private LocalDate reservationEndDate;
    private String reservationMemo;
    private ReservationStatus reservationStatus;

    private Long userId;
    private String nickname;
    private String address;
    private String phone;
    private String profileImageUrl;

    private List<PetResponseDto> pets;

    public ProvisionResponseDto(Reservation reservation) {
        this.reservationId = reservation.getReservationId();
        this.reservationStartDate = reservation.getReservationStartDate();
        this.reservationEndDate = reservation.getReservationEndDate();
        this.reservationMemo = reservation.getReservationMemo();
        this.reservationStatus = reservation.getReservationStatus();

        this.userId = reservation.getUser().getUserId();
        this.nickname = reservation.getUser().getNickname();
        this.address = reservation.getUser().getAddress();
        this.phone = reservation.getUser().getPhone();
        this.profileImageUrl = reservation.getUser().getProfileImageUrl();

        this.pets = reservation.getUser().getPet().stream()
                .map(PetResponseDto::new)
                .collect(Collectors.toList());
    }
}
