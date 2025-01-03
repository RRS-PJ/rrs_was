package com.korit.projectrrs.dto.reservation.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReservationUpdateRequestDto {
    @NotBlank
    private String reservationMemo;
}
