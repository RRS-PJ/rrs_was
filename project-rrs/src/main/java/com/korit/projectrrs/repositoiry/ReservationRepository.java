package com.korit.projectrrs.repositoiry;

import com.korit.projectrrs.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query(value = """
SELECT DISTINCT u.USER_ID,
FROM USERS u
JOIN AVAILABLE_DATE_OF_WEEK adw ON u.USER_ID = adw.PROVIDER_ID
WHERE adw.AVAILABLE_DATE BETWEEN :startDate AND :endDate
GROUP BY u.USER_ID
HAVING NOT EXISTS (
    SELECT 1
    FROM (
        SELECT DATE_ADD(:startDate, INTERVAL n DAY) AS required_date
        FROM (SELECT 0 AS n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3\s
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
}
