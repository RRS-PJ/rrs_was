package com.korit.projectrrs.dto.healthrecord.response;

import com.korit.projectrrs.entity.HealthRecord;
import com.korit.projectrrs.entity.HealthRecordAttachment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
public class HealthRecordResponseDto {
    private Long healthRecordId;
    private Long petId;
    private Double weight;
    private Short petAge;
    private String abnormalSymptoms;
    private String memo;
    private LocalDate createdAt;
    private List<String> attachments;

    public HealthRecordResponseDto(HealthRecord healthRecord) {
        this.healthRecordId = healthRecord.getHealthRecordId();
        this.petId = healthRecord.getPet().getPetId();
        this.weight = healthRecord.getWeight();
        this.petAge = healthRecord.getPetAge();
        this.abnormalSymptoms = healthRecord.getAbnormalSymptoms();
        this.memo = healthRecord.getMemo();
        this.createdAt = healthRecord.getCreatedAt();

        this.attachments = Optional.ofNullable(healthRecord.getAttachments())
                .orElseGet(Collections::emptyList)
                .stream()
                .map(HealthRecordAttachment::getHealthRecordAttachmentFile)
                .collect(Collectors.toList());
    }
}
