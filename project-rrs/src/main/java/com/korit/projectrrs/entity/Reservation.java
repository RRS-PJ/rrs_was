package com.korit.projectrrs.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID", nullable = false) // 데이터베이스의 "ID" 컬럼을 참조
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROVIDER_ID", nullable = false)
    private Provider provider;

    @Column(name = "RESERVATION_START_DATE")
    private LocalDate reservationStartDate;

    @Column(name = "RESERVATION_END_DATE")
    private LocalDate reservationEndDate;

    @Column(name = "RESERVATION_MEMO")
    private String reservationMemo;

    @Column(name = "RESERVATION_STATE", nullable = false)
    @Builder.Default
    private char reservationState = '0';
}