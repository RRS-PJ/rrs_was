package com.korit.projectrrs.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.korit.projectrrs.converter.ReservationStatusConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @Convert(converter = ReservationStatusConverter.class)
    @Column(name = "RESERVATION_STATUS", nullable = false, columnDefinition = "ENUM('예약대기', '예약 진행중', '거절', '취소', '완료') DEFAULT '예약대기'")
    private ReservationStatus reservationStatus = ReservationStatus.PENDING; // 기본값 '예약대기'

    @Builder.Default
    @Column(name = "RESERVATION_MEMO")
    private String reservationMemo = "";

    @OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Review review;
}