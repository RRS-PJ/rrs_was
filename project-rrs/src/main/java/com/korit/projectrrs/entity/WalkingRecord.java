package com.korit.projectrrs.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
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

    @JoinColumn(name = "petProfileId", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private PetProfile petProfile;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "DEFAULT 'SUNNY'")
    private WalkingRecordWeatherState walkingRecordWeatherState;

    @Column(nullable = false)
    private Integer walkingRecordDistance;

    @Column(nullable = false)
    private Duration walkingRecordWalkingTime;

    @Column(nullable = false)
    private LocalDate walkingRecordCreateAt;

    private String walkingRecordMemo;

    @Builder.Default
    @JsonManagedReference
    @OneToMany(mappedBy = "walkingRecord", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WalkingRecordAttachment> walkingRecordAttachments = new ArrayList<>();
}
