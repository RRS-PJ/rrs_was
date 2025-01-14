package com.korit.projectrrs.dto.provision.responseDto;

import com.korit.projectrrs.entity.Pet;
import com.korit.projectrrs.entity.Reservation;
import com.korit.projectrrs.entity.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvisionResponseDto {
        private Long reservationId;
        private LocalDate reservationStartDate;
        private LocalDate reservationEndDate;
        private List<Pet> pets;
        private ReservationStatus reservationStatus;

        public ProvisionResponseDto(Reservation reservation) {
            this.reservationId = reservation.getReservationId();
            this.reservationStartDate = reservation.getReservationStartDate();
            this.reservationEndDate = reservation.getReservationEndDate();
            this.pets = reservation.getUser().getPet();
            this.reservationStatus = reservation.getReservationStatus();
        }
}
