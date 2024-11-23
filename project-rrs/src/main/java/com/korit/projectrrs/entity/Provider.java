package com.korit.projectrrs.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROVIDER_ID", nullable = false)
    private Long providerId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    private String providerIntroduction;

    @Column(name = "PROVIDER_PROVISION_YN", nullable = false)
    private char providerProvisionYN;

    @Column(name = "MON", nullable = false)
    private char MON;

    @Column(name = "TUE", nullable = false)
    private char TUE;

    @Column(name = "WED", nullable = false)
    private char WED;

    @Column(name = "THU", nullable = false)
    private char THU;

    @Column(name = "FRI", nullable = false)
    private char FRI;

    @Column(name = "SAT", nullable = false)
    private char SAT;

    @Column(name = "SUN", nullable = false)
    private char SUN;

    @Builder.Default
    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();
}
