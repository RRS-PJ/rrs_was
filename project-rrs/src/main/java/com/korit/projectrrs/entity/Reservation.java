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
    @Column(name = "RESERVATION_ID")
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
    @Column(name = "RESERVATION_STATUS", nullable = false, columnDefinition = "ENUM('예약대기', '예약 진행중', '거절', '취소', '완료') DEFAULT '예약대기'")
    private ReservationStatus reservationStatus = ReservationStatus.PENDING; // 기본값 '예약대기'
}