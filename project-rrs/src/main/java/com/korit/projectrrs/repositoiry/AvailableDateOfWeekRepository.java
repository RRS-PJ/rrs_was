package com.korit.projectrrs.repositoiry;

import com.korit.projectrrs.entity.AvailableDateOfWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AvailableDateOfWeekRepository extends JpaRepository<AvailableDateOfWeek, Long> {
    @Query("SELECT a " +
            "FROM AvailableDateOfWeek a " +
            "WHERE a.provider.userId = :userId " +
            "AND a.availableDateOfWeekId = :availableDateOfWeekId")
    Optional<AvailableDateOfWeek> findByUserIdAndAvailableDateAndId(@Param("userId") Long userId, @Param("availableDateOfWeekId") Long availableDateOfWeekId);


    @Query("SELECT a " +
            "FROM AvailableDateOfWeek a " +
            "WHERE a.provider.userId = :userId ")
    List<AvailableDateOfWeek> findAllAvailableDateByUserId(@Param("userId") Long userId);

    void deleteByProvider_UserIdAndAvailableDate(Long userId, LocalDate availableDate);
}