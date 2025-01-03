package com.korit.projectrrs.entity;

import lombok.Getter;

@Getter
public enum ReservationStatus {
    PENDING(),
    IN_PROGRESS,
    REJECTED,
    CANCELED,
    COMPLETED;
}