package com.korit.projectrrs.converter;

import com.korit.projectrrs.common.ResponseMessage;
import com.korit.projectrrs.entity.ReservationStatus;
import jakarta.persistence.AttributeConverter;

public class ReservationStatusConverter implements AttributeConverter<ReservationStatus, String> {

    @Override
    public String convertToDatabaseColumn(ReservationStatus status) {
        if (status == null) return null;
        return status.getStatus(); // 한글 상태값을 데이터베이스에 저장
    }

    @Override
    public ReservationStatus convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;

        for (ReservationStatus status : ReservationStatus.values()) {
            if (status.getStatus().equals(dbData)) {
                return status;
            }
        }
        throw new IllegalArgumentException(ResponseMessage.UNKNOWN_STATUS + dbData);
    }
}
