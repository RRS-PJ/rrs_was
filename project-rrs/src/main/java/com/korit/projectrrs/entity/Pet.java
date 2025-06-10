package com.korit.projectrrs.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'pet-default-profile.jpg")
    private String petImageUrl;

    private String petAddInfo;

    private Character petNeutralityYn;

    @Builder.Default
    @JsonManagedReference
    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<WalkingRecord> walkingRecords = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<HealthRecord> healthRecords;

    // 헬퍼 메서드 추가: userId 값을 반환
    public Long getUserId() {
        return user != null ? user.getUserId() : null;
    }
}