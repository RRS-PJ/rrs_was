package com.korit.projectrrs.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "petProfiles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class PetProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long petProfileId;

    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private User user;

    @Column(nullable = false)
    private String petProfileName;

    @Column(nullable = false)
    private Character petProfileGender;

    @Column(nullable = false)
    private String petProfileBirthDate;

    @Column(nullable = false)
    private Integer petProfileWeight;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'petExample.jpg'")
    private String petProfileImageUrl;

    private String petProfileAddInfo;

    private Character petProfileNeutralityYn;

    @Builder.Default
    @JsonManagedReference
    @OneToMany(mappedBy = "petProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WalkingRecord> walkingRecords = new ArrayList<>();
}
