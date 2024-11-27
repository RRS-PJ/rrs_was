package com.korit.projectrrs.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Data
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REVIEW_ID", nullable = false)
    private Long reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID", nullable = false) // 데이터베이스의 "ID" 컬럼을 참조
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROVIDER_ID", nullable = false)
    private Provider provider;

    @Column(name = "REVIEW_CREATE_AT")
    private LocalDateTime reviewCreateAt;

    @Builder.Default
    @Column(name = "REVIEW_SCORE")
    private int reviewScore = 0;

    @Column(name = "REVIEW_CONTENT")
    private String reviewContent;
}