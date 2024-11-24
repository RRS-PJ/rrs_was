package com.korit.projectrrs.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PetProfiles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class PetProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long petProfileId;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User userId;

    @Column(nullable = false)
    private String petProfileName;

    @Column(nullable = false)
    private Character petProfileGender;

    @Column(nullable = false)
    private String petProfileBirthDate;

    @Column(nullable = false)
    private int petProfileWeight;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'petExample.jpg'")
    private String petProfileImageUrl;

    private String petProfileAddInfo;

    private Character petProfileNeutralityYn;
}
