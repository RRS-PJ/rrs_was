package com.korit.projectrrs.repositoiry;

import com.korit.projectrrs.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query(value = """
SELECT DISTINCT u.USER_ID
FROM `USERS` u
JOIN `AVAILABLE_DATE_OF_WEEK` adw ON u.USER_ID = adw.PROVIDER_ID
WHERE adw.AVAILABLE_DATE BETWEEN :startDate AND :endDate
AND NOT EXISTS (
    SELECT 1
    FROM `RESERVATIONS` r
    WHERE r.PROVIDER_ID = u.USER_ID
    AND r.RESERVATION_START_DATE <= :endDate
    AND r.RESERVATION_END_DATE >= :startDate
)
GROUP BY u.USER_ID
HAVING NOT EXISTS (
    SELECT 1
    FROM (
        SELECT DATE_ADD(:startDate, INTERVAL n DAY) AS required_date
        FROM (SELECT 0 AS n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3
              UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6) days
        WHERE DATE_ADD(:startDate, INTERVAL n DAY) <= :endDate
    ) required_dates
    WHERE NOT EXISTS (
        SELECT 1
        FROM AVAILABLE_DATE_OF_WEEK adw_inner
        WHERE adw_inner.PROVIDER_ID = u.USER_ID
        AND adw_inner.AVAILABLE_DATE = required_dates.required_date
    )
);
""", nativeQuery = true)
    Set<Long> findProviderByDate(@Param("startDate")LocalDate startDate, @Param("endDate")LocalDate endDate);

    @Query(value = """
SELECT
    R.*
FROM 
    RESERVATIONS R
WHERE
    R.USER_ID = :userId
""", nativeQuery = true)
    List<Reservation> findAllByUserId(@Param("userId") Long userId);

    @Query(value = """
SELECT
    R.*
FROM 
    RESERVATIONS R
    INNER JOIN USERS U ON U.USER_ID = R.PROVIDER_ID
WHERE
    R.PROVIDER_ID = :providerId
    AND U.ROLES LIKE '%ROLE_PROVIDER%'
""", nativeQuery = true)
    List<Reservation> findAllByProviderId(@Param("providerId") Long providerId);

    @Query(value = """
SELECT 
     CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END
FROM 
    RESERVATIONS r
WHERE 
    r.PROVIDER_ID = :providerId
AND 
    r.RESERVATION_START_DATE <= :reservationEndDate
AND 
    r.RESERVATION_END_DATE >= :reservationStartDate
""", nativeQuery = true)
    int existsByProviderAndDateRange(
            @Param("providerId") Long providerId,
            @Param("reservationStartDate") LocalDate reservationStartDate,
            @Param("reservationEndDate") LocalDate reservationEndDate);
}