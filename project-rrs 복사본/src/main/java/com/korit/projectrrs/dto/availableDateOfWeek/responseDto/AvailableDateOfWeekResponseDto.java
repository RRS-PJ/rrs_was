package com.korit.projectrrs.dto.availableDateOfWeek.responseDto;

import com.korit.projectrrs.entity.AvailableDateOfWeek;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableDateOfWeekResponseDto {
    private LocalDate availableDate;

    public AvailableDateOfWeekResponseDto(AvailableDateOfWeek availableDateOfWeek) {
        this.availableDate = availableDateOfWeek.getAvailableDate();
    }
}
