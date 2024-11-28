package com.korit.projectrrs.repositoiry;

import com.korit.projectrrs.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
    @Query(value =
            "SELECT p.PROVIDER_ID" +
            "FROM PROVIDERS p" +
            "WHERE NOT EXISTS (" +
            "    SELECT 1" +
            "    FROM (" +
            "        SELECT DATE_ADD((SELECT MIN(RESERVATION_START_DATE) FROM RESERVATIONS), INTERVAL n DAY) AS RESERVATION_DATE" +
            "        FROM (SELECT @rownum := @rownum + 1 AS n FROM RESERVATIONS, (SELECT @rownum := 0) r) days" +
            "        WHERE DATE_ADD((SELECT MIN(RESERVATION_START_DATE) FROM RESERVATIONS), INTERVAL n DAY) <= (SELECT MAX(RESERVATION_END_DATE) FROM RESERVATIONS)" +
            "    ) AS reservation_dates" +
            "    WHERE NOT EXISTS (" +
            "        SELECT 1" +
            "        FROM AVAILABLE_DATE_OF_WEEK adw" +
            "        WHERE adw.PROVIDER_ID = p.PROVIDER_ID AND adw.AVAILABLE_DATE = reservation_dates.RESERVATION_DATE" +
            "    )" +
            ")" +
            "ORDER BY p.PROVIDER_ID;"
            , nativeQuery = true)
    Optional<List<Long>> findProviderIdByAvailableDate(
            @Param("reservationStartDate") LocalDate reservationStartDate,
            @Param("reservationEndDate") LocalDate reservationEndDate
            );
}