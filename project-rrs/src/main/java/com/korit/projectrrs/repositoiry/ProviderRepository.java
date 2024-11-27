package com.korit.projectrrs.repositoiry;

import com.korit.projectrrs.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
    List<Provider> findProvidersAvailableOnRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}