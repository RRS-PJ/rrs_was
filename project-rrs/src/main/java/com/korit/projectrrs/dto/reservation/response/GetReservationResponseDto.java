package com.korit.projectrrs.dto.reservation.response;

import com.korit.projectrrs.entity.Reservation;
import com.korit.projectrrs.entity.ReservationStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GetReservationResponseDto {
    private Long reservationId;
    private LocalDate reservationStartDate;
    private LocalDate reservationEndDate;
    private GetChosenProviderInfoResponseDto providerInfo;
    private String reservationMemo;
    private ReservationStatus reservationStatus;

    public GetReservationResponseDto(Reservation reservation, double avgReviewScore){
        this.reservationId = reservation.getReservationId();
        this.reservationStartDate = reservation.getReservationStartDate();
        this.reservationEndDate = reservation.getReservationEndDate();
        this.providerInfo = new GetChosenProviderInfoResponseDto(reservation.getProvider(), avgReviewScore);
        this.reservationMemo = reservation.getReservationMemo();
        this.reservationStatus = reservation.getReservationStatus();
    }
}
