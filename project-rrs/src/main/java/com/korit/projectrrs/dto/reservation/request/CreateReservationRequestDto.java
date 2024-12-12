package com.korit.projectrrs.dto.reservation.request;

import com.korit.projectrrs.entity.ReservationStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateReservationRequestDto {
    private Long userId;
    private Long providerId;
    private LocalDate reservationStartDate;
    private LocalDate reservationEndDate;
    private ReservationStatus reservationStatus; // 기본값 '예약대기'
}
