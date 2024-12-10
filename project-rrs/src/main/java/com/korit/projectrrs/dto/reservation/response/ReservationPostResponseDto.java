package com.korit.projectrrs.dto.reservation.response;

import com.korit.projectrrs.entity.Pet;
import com.korit.projectrrs.entity.Reservation;
import com.korit.projectrrs.entity.ReservationStatus;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder(toBuilder = true)
@RequiredArgsConstructor
public class ReservationPostResponseDto {
    private GetChosenProviderInfoResponseDto providerInfo;

    private LocalDate reservationStartDate;
    private LocalDate reservationEndDate;

    private List<Pet> pets;
    private String phone;
    private String reservationMemo;

    private ReservationStatus reservationStatus;

    public ReservationPostResponseDto(Reservation reservation, double avgReviewScore){
        this.providerInfo = new GetChosenProviderInfoResponseDto(reservation.getProvider(), avgReviewScore);

        this.reservationStartDate = reservation.getReservationStartDate();
        this.reservationEndDate = reservation.getReservationEndDate();

        this.pets = reservation.getUser().getPet();
        this.phone = reservation.getUser().getPhone();

        this.reservationMemo = reservation.getReservationMemo();
        this.reservationStatus = reservation.getReservationStatus();
    }
}