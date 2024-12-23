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
@Table(name = "WalkingRecords")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class WalkingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long walkingRecordId;

    @JoinColumn(name = "petId", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Pet pet;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WalkingRecordWeatherState walkingRecordWeatherState = WalkingRecordWeatherState.SUNNY;

    @Column(nullable = false)
    private Integer walkingRecordDistance;

    @Column(nullable = false)
    private Integer walkingRecordWalkingTime;

    @Column(nullable = false)
    private LocalDate walkingRecordCreateAt;

    private String walkingRecordMemo;

    @Builder.Default
    @OneToMany(mappedBy = "walkingRecord", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WalkingRecordAttachment> walkingRecordAttachments = new ArrayList<>();
}
