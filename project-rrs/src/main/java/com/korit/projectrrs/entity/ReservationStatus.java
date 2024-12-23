package com.korit.projectrrs.entity;

import lombok.Getter;

@Getter
public enum ReservationStatus {
    PENDING("PENDING"),
    IN_PROGRESS("IN_PROGRESS"),
    REJECTED("REJECTED"),
    CANCELED("CANCELED"),
    COMPLETED("COMPLETED");

    private String status;

    ReservationStatus(String status) {
        this.status = status;
    }
}