package com.korit.projectrrs.dto.reservation.request;

import com.korit.projectrrs.entity.ReservationStatus;
import lombok.Data;

@Data
public class ReviewCancelRequestDto {
    private ReservationStatus reservationStatus;
}
