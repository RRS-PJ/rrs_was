package com.korit.projectrrs.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @Column(name = "REVIEW_ID", nullable = false, unique = true)
    private Long reviewId;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user; // USER_ID와 연결된 USER 엔티티

    @OneToOne
    @JoinColumn(name = "RESERVATION_ID", nullable = false)
    private Reservation reservation;

    @Column(name = "REVIEW_CREATE_AT", nullable = false)
    private LocalDateTime reviewCreatedAt;

    @Column(name = "REVIEW_SCORE", nullable = false)
    private Double reviewScore; // 별점 (SmallInt로 정의됨)

    @Column(name = "REVIEW_CONTENT", nullable = false)
    private String reviewContent; // 리뷰 내용
}
