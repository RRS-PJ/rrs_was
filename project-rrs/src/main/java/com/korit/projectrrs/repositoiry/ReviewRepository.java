package com.korit.projectrrs.repositoiry;

import com.korit.projectrrs.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
