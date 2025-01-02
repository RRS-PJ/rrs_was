package com.korit.projectrrs.repositoiry;

import com.korit.projectrrs.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query(value = """
SELECT
    R.*
FROM
    REVIEWS R
    INNER JOIN RESERVATIONS RV ON RV.RESERVATION_ID = R.RESERVATION_ID
    INNER JOIN USERS U ON U.USER_ID = RV.PROVIDER_ID
WHERE
    U.USER_ID = :providerId
    AND U.ROLES LIKE '%ROLE_PROVIDER%'
""", nativeQuery = true)
    List<Review> findReviewsByProvider(@Param("providerId") Long providerId);

    @Query(value = """
SELECT
    R.*
FROM
    REVIEWS R
    INNER JOIN RESERVATIONS RV ON RV.RESERVATION_ID = R.RESERVATION_ID
    INNER JOIN USERS U ON U.USER_ID = RV.PROVIDER_ID
WHERE
    U.USER_ID = :providerId
    AND U.ROLES LIKE '%ROLE_PROVIDER%'
ORDER BY
    R.REVIEW_CREATE_AT DESC
LIMIT 1
""", nativeQuery = true)
    Optional<Review> findLatestReviewByProviderId(@Param("providerId") Long providerId);

    // 제공자 ID으로 평균 리뷰 조회
    @Query(value = """
SELECT 
    AVG(R.REVIEW_SCORE)
FROM
    REVIEWS R
    INNER JOIN RESERVATIONS RV ON RV.RESERVATION_ID = R.RESERVATION_ID
    INNER JOIN USERS U ON U.USER_ID = RV.PROVIDER_ID
WHERE
    U.USER_ID = :providerId\s
    AND U.ROLES LIKE '%ROLE_PROVIDER%'
""", nativeQuery = true)
    Optional<Double> findAvgReviewScoreByProvider(@Param("providerId") Long providerId);

    @Query(value = """
    SELECT 
        RV.PROVIDER_ID, AVG(R.REVIEW_SCORE) AS AVG_SCORE
    FROM 
        REVIEWS R
        LEFT OUTER JOIN RESERVATIONS RV ON RV.RESERVATION_ID = R.RESERVATION_ID
        LEFT OUTER JOIN USERS U ON U.USER_ID = RV.PROVIDER_ID
    WHERE 
        U.USER_ID IN (:providerUserIds)
        AND U.ROLES LIKE '%ROLE_PROVIDER%'
    GROUP BY RV.PROVIDER_ID
""", nativeQuery = true)
    Map<Long, Double> findAverageReviewScoresByProviders(@Param("providerUserIds") Set<Long> providerUserIds);

    boolean existsByReservation_ReservationId(Long reservationId);
}