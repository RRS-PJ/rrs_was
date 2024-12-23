package com.korit.projectrrs.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "AVAILABLE_DATE_OF_WEEK")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class AvailableDateOfWeek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AVAILABLE_DATE_OF_WEEK_ID")
    private Long availableDateOfWeekId;

    @JoinColumn(name = "PROVIDER_ID", referencedColumnName = "USER_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User provider;

    private LocalDate availableDate;
}
