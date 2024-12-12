package com.korit.projectrrs.dto.reservation.response;

import com.korit.projectrrs.entity.Pet;
import com.korit.projectrrs.entity.Reservation;
import com.korit.projectrrs.entity.ReservationStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class GetReservationResponseDto {
    private LocalDate reservationStartDate;
    private LocalDate reservationEndDate;

    private GetChosenProviderInfoResponseDto providerInfo;

    private List<Pet> pets;
    private String phone;
    private String reservationMemo;

    private ReservationStatus reservationStatus;

    public GetReservationResponseDto(Reservation reservation, double avgReviewScore){
        this.reservationStartDate = reservation.getReservationStartDate();
        this.reservationEndDate = reservation.getReservationEndDate();

        this.providerInfo = new GetChosenProviderInfoResponseDto(reservation.getProvider(), avgReviewScore);

        this.pets = reservation.getUser().getPet();
        this.phone = reservation.getUser().getPhone();

        this.reservationMemo = reservation.getReservationMemo();
        this.reservationStatus = reservation.getReservationStatus();
    }
}
