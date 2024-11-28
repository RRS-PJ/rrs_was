package com.korit.projectrrs.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "petProfiles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class PetProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long petProfileId;

    @JoinColumn(name = "ID", referencedColumnName = "ID", nullable = false)
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
}
