package com.korit.projectrrs.dto.reservation.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FindProviderByDateRequestDto {
    private LocalDate startDate;
    private LocalDate endDate;
}
