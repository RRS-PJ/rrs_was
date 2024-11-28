package com.korit.projectrrs.dto.reservation.request;

import com.korit.projectrrs.entity.Reservation;
import lombok.Data;

@Data
public class ReservationPutRequestDto {
    private String reservationMemo;

    public ReservationPutRequestDto(Reservation reservation){
        this.reservationMemo = reservation.getReservationMemo();
    }
}
