package com.korit.projectrrs.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "HEALTH_RECORDS")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class HealthRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HEALTH_RECORD_ID", nullable = false, updatable = false)
    private Long healthRecordId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PET_ID", nullable = false)
    @JsonBackReference
    private Pet pet;

    @Column(name = "HEALTH_RECORD_WEIGHT", nullable = false)
    private Double weight;

    @Column(name = "HEALTH_RECORD_PET_AGE", nullable = false)
    private Short petAge;

    @Column(name = "HEALTH_RECORD_ABNORMAL_SYMPTOMS", nullable = false, length = 255)
    private String abnormalSymptoms;

    @Column(name = "HEALTH_RECORD_CREATE_AT", nullable = false)
    private LocalDate createdAt;

    @Column(name = "HEALTH_RECORD_MEMO", columnDefinition = "TEXT")
    private String memo;

    @OneToMany(mappedBy = "healthRecord", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HealthRecordAttachment> attachments = new ArrayList<>();

    // 헬퍼 메서드 추가: Pet의 사용자 ID 반환
    public Long getPetId() {
        return pet != null && pet.getUser() != null ? pet.getUser().getUserId() : null;
    }

}
