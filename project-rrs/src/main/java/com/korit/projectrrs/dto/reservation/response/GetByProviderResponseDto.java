package com.korit.projectrrs.dto.reservation.response;

import com.korit.projectrrs.entity.Pet;
import com.korit.projectrrs.entity.Reservation;
import com.korit.projectrrs.entity.ReservationStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class GetByProviderResponseDto {
    private LocalDate reservationStartDate;
    private LocalDate reservationEndDate;

    private List<Pet> pets;
    private String phone;

    private String reservationMemo;

    private ReservationStatus reservationStatus;

    public GetByProviderResponseDto(Reservation reservation){
        this.reservationStartDate = reservation.getReservationStartDate();
        this.reservationEndDate = reservation.getReservationEndDate();

        this.pets = reservation.getUser().getPet();
        this.phone = reservation.getUser().getPhone();

        this.reservationMemo = reservation.getReservationMemo();
        this.reservationStatus = reservation.getReservationStatus();
    }
}
