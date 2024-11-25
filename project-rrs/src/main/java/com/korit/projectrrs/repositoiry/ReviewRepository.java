package com.korit.projectrrs.repositoiry;

import com.korit.projectrrs.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // 특정 댕시터에 대한 모든 리뷰 조회
    @Query("SELECT r " +
            "FROM Review r " +
            "WHERE r.provider = :providerId")
    Optional<List<Review>> findReviewsByProvider(@Param("providerId") Long providerId);

    // 제공자 아이디로 평균 reviewScore 조회
    @Query("SELECT AVG(r.reviewScore) " +
            "FROM Review r " +
            "WHERE r.provider = :providerId")
    Double findAverageReviewScoreByProvider(@Param("providerId") Long providerId);
}
