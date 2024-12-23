package com.korit.projectrrs.dto.reservation.response;

import com.korit.projectrrs.entity.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateStatusResponseDto {
    private ReservationStatus reservationStatus;
}
