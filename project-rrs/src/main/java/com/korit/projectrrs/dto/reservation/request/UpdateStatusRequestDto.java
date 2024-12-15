package com.korit.projectrrs.dto.reservation.request;

import com.korit.projectrrs.entity.ReservationStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateStatusRequestDto {
    @NotBlank
    private Long reservationId;

    @NotBlank
    private ReservationStatus reservationStatus;
}
