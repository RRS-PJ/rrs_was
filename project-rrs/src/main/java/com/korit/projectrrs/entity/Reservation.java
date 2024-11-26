package com.korit.projectrrs.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
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

    @Column(name = "RESERVATION_STATE", nullable = false)
    private char reservationState;
}