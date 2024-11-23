package com.korit.projectrrs.repositoiry;

import com.korit.projectrrs.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT r " +
            "FROM Review r " +
            "WHERE r.provider.providerId = :providerId"
    )
    Optional<List<Review>> findAllByProiderId(@Param("providerId") Long providerId);
}
