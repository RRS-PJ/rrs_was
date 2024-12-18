package com.korit.projectrrs.dto.reservation.request;

import com.korit.projectrrs.entity.ReservationStatus;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateReservationRequestDto {
    @NotBlank
    private Long providerId;

    @NotNull
    @Future
    private LocalDate reservationStartDate;

    @NotNull
    @Future
    private LocalDate reservationEndDate;

    private ReservationStatus reservationStatus; // 기본값 '예약대기'
}
