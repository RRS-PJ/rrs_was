package com.korit.projectrrs.dto.reservation.request;

import com.korit.projectrrs.entity.Reservation;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationPostRequestDto {
    private LocalDate reservationStartDate;
    private LocalDate reservationEndDate;
    private Long ChosenProviderId;
    private String reservationMemo;

    public ReservationPostRequestDto(Reservation reservation){
        this.reservationStartDate = reservation.getReservationStartDate();
        this.reservationEndDate = reservation.getReservationEndDate();
        this.ChosenProviderId = reservation.getProvider().getProviderId();
        this.reservationMemo = reservation.getReservationMemo();
    }
}
