package com.korit.projectrrs.dto.healthrecord.response;

import com.korit.projectrrs.entity.HealthRecord;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class HealthRecordAllResponseDto {
    private Long healthRecordId;
    private Long petId;
    private Double weight;
    private Short petAge;
    private String abnormalSymptoms;
    private String memo;
    private LocalDate createdAt;

    public HealthRecordAllResponseDto(HealthRecord healthRecord) {
        this.healthRecordId = healthRecord.getHealthRecordId();
        this.petId = healthRecord.getPetId();
        this.weight = healthRecord.getWeight();
        this.petAge = healthRecord.getPetAge();
        this.abnormalSymptoms = healthRecord.getAbnormalSymptoms();
        this.memo = healthRecord.getMemo();
        this.createdAt = healthRecord.getCreatedAt();
    }
}
