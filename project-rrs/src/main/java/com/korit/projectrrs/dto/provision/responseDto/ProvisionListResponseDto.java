package com.korit.projectrrs.dto.provision.responseDto;

import com.korit.projectrrs.entity.Reservation;
import com.korit.projectrrs.entity.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvisionListResponseDto {
    private Long reservationId;
    private LocalDate reservationStartDate;
    private LocalDate reservationEndDate;
    private Long userId;
    private String nickname;
    private ReservationStatus reservationStatus;

    public ProvisionListResponseDto(Reservation reservation) {
        this.reservationId = reservation.getReservationId();
        this.reservationStartDate = reservation.getReservationStartDate();
        this.reservationEndDate = reservation.getReservationEndDate();
        this.userId = reservation.getUser().getUserId();
        this.nickname = reservation.getUser().getNickname();
        this.reservationStatus = reservation.getReservationStatus();
    }
}