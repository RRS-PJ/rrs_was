package com.korit.projectrrs.dto.reservation.response;

import com.korit.projectrrs.entity.PetProfile;
import com.korit.projectrrs.entity.Provider;
import com.korit.projectrrs.entity.Reservation;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder(toBuilder = true)
public class ReservationPostResponseDto {
    private LocalDate reservationStartDate;
    private LocalDate reservationEndDate;
    private Provider provider;
    private List<PetProfile> pets;
    private String userPhone;
    private String reservationMemo;

    public ReservationPostResponseDto(Reservation reservation){
        this.reservationStartDate = reservation.getReservationStartDate();
        this.reservationEndDate = reservation.getReservationEndDate();
        this.provider = reservation.getProvider();
        this.reservationMemo = reservation.getReservationMemo();
    }
}