package com.korit.projectrrs.dto.reservation.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationGetResponseDto {
    private LocalDate reservationStartDate;
    private LocalDate reservationEndDate;
    private Long ChosenProviderId;
    private String reservationMemo;
    private char reservationState;
}
