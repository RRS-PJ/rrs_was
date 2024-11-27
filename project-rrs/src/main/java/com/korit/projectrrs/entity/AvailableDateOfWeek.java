package com.korit.projectrrs.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@Table(name = "AVAILABLE_DATE_OF_WEEK")
@NoArgsConstructor
@AllArgsConstructor
public class AvailableDateOfWeek{
    @EmbeddedId
    private AvailableDateOfWeekId id;

    @Column(name = "IS_AVAILABLE", nullable = false)
    private String isAvailable;
}
