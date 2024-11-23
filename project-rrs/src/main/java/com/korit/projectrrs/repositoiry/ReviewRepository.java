package com.korit.projectrrs.repositoiry;

import com.korit.projectrrs.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<List<Review>> findAllByProiderId(Long providerId);

    Optional<List<Review>> findByUserId();
}
