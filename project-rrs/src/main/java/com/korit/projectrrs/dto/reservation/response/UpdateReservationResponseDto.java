package com.korit.projectrrs.dto.reservation.response;

import com.korit.projectrrs.entity.Reservation;
import lombok.Data;

@Data
public class UpdateReservationResponseDto {
    private String reservationMemo;

    public UpdateReservationResponseDto(Reservation reservation){
        this.reservationMemo = reservation.getReservationMemo();
    }
}