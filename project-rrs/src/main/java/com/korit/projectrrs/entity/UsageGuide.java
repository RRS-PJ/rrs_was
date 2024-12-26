package com.korit.projectrrs.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "USAGE_GUIDES")
@Data
@NoArgsConstructor
public class UsageGuide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GUIDE_ID")
    private Long guideId;

    @Column(name = "GUIDE_TITLE", nullable = false)
    private String guideTitle;

    @Column(name = "GUIDE_CONTENT", nullable = false)
    private String guideContent;

    @Column(name = "GUIDE_CREATE_AT", nullable = false)
    private LocalDateTime guideCreatedAt;

    @PrePersist
    protected void onCreate() {
        this.guideCreatedAt = LocalDateTime.now();
    }
}
