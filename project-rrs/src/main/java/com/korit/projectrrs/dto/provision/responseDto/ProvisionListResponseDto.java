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
public class ProvisionListResponseDto {
    private Long reservationId;
    private LocalDate reservationStartDate;
    private LocalDate reservationEndDate;
    private List<Pet> pets;
    private String nickName;
    private String address;
    private String addressDetail;
    private String phone;
    private String reservationMemo;
    private ReservationStatus reservationStatus;

    public ProvisionListResponseDto(Reservation reservation) {
        this.reservationId = reservation.getReservationId();
        this.reservationStartDate = reservation.getReservationStartDate();
        this.reservationEndDate = reservation.getReservationEndDate();
        this.pets = reservation.getUser().getPet();
        this.nickName = reservation.getUser().getNickname();
        this.address = reservation.getUser().getAddress();
        this.addressDetail = reservation.getUser().getAddressDetail();
        this.phone = reservation.getUser().getPhone();
        this.reservationMemo = reservation.getReservationMemo();
        this.reservationStatus = reservation.getReservationStatus();
    }
}
