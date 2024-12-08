package com.korit.projectrrs.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pets")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long petId;

    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private User user;

    @Column(nullable = false)
    private String petName;

    @Column(nullable = false)
    private Character petGender;

    @Column(nullable = false)
    private String petBirthDate;

    @Column(nullable = false)
    private Integer petWeight;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'petExample.jpg'")
    private String petImageUrl;

    private String petAddInfo;

    private Character petNeutralityYn;

    @Builder.Default
    @JsonManagedReference
    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WalkingRecord> walkingRecords = new ArrayList<>();
}
