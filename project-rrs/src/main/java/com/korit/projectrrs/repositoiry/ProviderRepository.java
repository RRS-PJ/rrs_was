package com.korit.projectrrs.repositoiry;

import com.korit.projectrrs.entity.Provider;
import com.korit.projectrrs.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
    @Query("SELECT p FROM Provider p WHERE p.providerProvisionYN = '1' AND " +
            "(p.mon = '1' AND :startDate <= :endDate) AND " +
            "(p.tue = '1' AND :startDate <= :endDate) AND " +
            "(p.wed = '1' AND :startDate <= :endDate) AND " +
            "(p.thu = '1' AND :startDate <= :endDate) AND " +
            "(p.fri = '1' AND :startDate <= :endDate) AND " +
            "(p.sat = '1' AND :startDate <= :endDate) AND " +
            "(p.sun = '1' AND :startDate <= :endDate) AND " +
            "((p.sun = '1' AND p.mon = '1') OR (p.mon = '1'))")
    List<Provider> findProvidersAvailableOnRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    // 특정 댕시터에 대한 모든 리뷰 조회
    @Query("SELECT r " +
            "FROM Review r " +
            "WHERE r.provider = :providerId")
    List<Review> findReviewsByProvider(@Param("providerId") Long providerId);

    // 제공자 닉네임으로 평균 리뷰 조회
    @Query("SELECT AVG(r.reviewScore) " +
            "FROM Review r " +
            "WHERE r.provider = :providerId")
    Double findAverageReviewScoreByProvider(@Param("providerId") Long providerId);
}