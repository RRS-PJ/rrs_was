package com.korit.projectrrs.dto.reservation.response;

import com.korit.projectrrs.entity.PetProfile;
import com.korit.projectrrs.entity.Provider;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ReservationPostResponseDto {
    private LocalDate reservationStartDate;
    private LocalDate reservationEndDate;
    private Provider provider;
    private String reservationMemo;
    private List<PetProfile> pets;
    private String userPhone;
    private List<Long> providersId;
}