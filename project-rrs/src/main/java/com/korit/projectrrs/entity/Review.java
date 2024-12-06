package com.korit.projectrrs.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "REVIEWS")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REVIEW_ID")
    private Long reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user; // USER_ID와 연결된 USER 엔티티

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROVIDER_ID", nullable = false)
    private User provider; // PROVIDER_ID와 연결된 USER 엔티티

    @Column(name = "REVIEW_CREATE_AT", nullable = false)
    private LocalDateTime reviewCreatedAt;

    @Column(name = "REVIEW_SCORE", nullable = false)
    private int reviewScore; // 별점 (SmallInt로 정의됨)

    @Column(name = "REVIEW_CONTENT", nullable = false)
    private String reviewContent; // 리뷰 내용
}
