package com.korit.projectrrs.service;

import com.korit.projectrrs.entity.AvailableDateOfWeek;
import java.time.LocalDate;

public interface AvailableDateOfWeekService {
    AvailableDateOfWeek addDate(Long userId, LocalDate availableDate);
    void reomoveDate(Long userId, Long availableDateOfWeekId);
}
