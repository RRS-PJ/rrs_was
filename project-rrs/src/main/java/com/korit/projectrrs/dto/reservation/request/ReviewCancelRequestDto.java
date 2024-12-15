package com.korit.projectrrs.dto.reservation.request;

import com.korit.projectrrs.entity.ReservationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewCancelRequestDto {
    @NotNull
    private ReservationStatus reservationStatus;
}
