package com.korit.projectrrs.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "RESERVATIONS")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESERVATION_ID", nullable = false, unique = true)
    private Long reservationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROVIDER_ID", nullable = false)
    private User provider;

    @Column(name = "RESERVATION_START_DATE", nullable = false)
    private LocalDate reservationStartDate;

    @Column(name = "RESERVATION_END_DATE", nullable = false)
    private LocalDate reservationEndDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "RESERVATION_STATUS", nullable = false, columnDefinition = "ENUM('PENDING', 'IN_PROGRESS' , 'REJECTED', CANCELED, COMPLETED' DEFAULT 'PENDING'")
    private ReservationStatus reservationStatus; // 기본값 '예약대기'

    @Builder.Default
    @Column(name = "RESERVATION_MEMO")
    private String reservationMemo = "";

    @OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Review review;
}