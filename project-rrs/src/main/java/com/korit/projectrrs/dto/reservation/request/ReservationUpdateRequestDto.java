package com.korit.projectrrs.dto.reservation.request;

import lombok.Data;

@Data
public class ReservationUpdateRequestDto {
    private Long reservationId;
    private String reservationMemo;
}
