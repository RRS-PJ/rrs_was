package com.korit.projectrrs.entity;

public enum ReservationStatus {
    PENDING("예약대기"),
    IN_PROGRESS("예약 진행중"),
    REJECTED("거절"),
    CANCELED("취소"),
    COMPLETED("완료");

    private String status;

    ReservationStatus(String status) {
        this.status = status;
    }
}