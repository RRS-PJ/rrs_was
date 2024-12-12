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
    *
FROM 
    REVIEWS R
INNER JOIN USERS U ON U.USER_ID = R.PROVIDER_ID
WHERE 
    R.PROVIDER_ID = :providerId
    AND U.ROLES LIKE '%, ROLE_PROVIDER%'
""", nativeQuery = true)
    Optional<List<Review>>  findReviewsByProvider(@Param("providerId") Long providerId);

    // 제공자 닉네임으로 평균 리뷰 조회
    @Query(value = """
SELECT
    AVG(r.reviewScores)
FROM
    REVIEWS R
INNER JOIN USERS U ON U.USER_ID = R.PROVIDER_ID
WHERE
    R.PROVIDER_ID = :providerId
    AND U.ROLES LIKE '%, ROLE_PROVIDER%'
""", nativeQuery = true)
    Optional<Double> findAverageReviewScoreByProvider(@Param("providerId") Long providerId);

    @Query(value = """
        SELECT r.provider_user_id, AVG(r.score) 
        FROM reviews r 
        WHERE r.provider_user_id IN :providerUserIds 
        GROUP BY r.provider_user_id
    """, nativeQuery = true)
    Map<Long, Double> findAverageReviewScoresByProviders(@Param("providerUserIds") Set<Long> providerUserIds);
}
