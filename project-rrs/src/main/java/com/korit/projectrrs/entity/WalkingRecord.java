package com.korit.projectrrs.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "WalkingRecords")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class WalkingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long walkingRecordId;

    @JoinColumn(name = "petProfileId", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private PetProfile petProfile;

    @Column(nullable = false, columnDefinition = "CHAR(1) DEFAULT '0'")
    private Character walkingRecordWeatherState;

    @Column(nullable = false)
    private int walkingRecordDistance;

    @Column(nullable = false)
    private LocalTime walkingRecordWalkingTime;

    @Column(nullable = false)
    private LocalDateTime walkingRecordCreateAt;

    private String walkingRecordMemo;

    private String walkingRecordImageUrl;
}
