package com.korit.projectrrs.repositoiry;

import com.korit.projectrrs.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface reservationRepository extends JpaRepository<Reservation, Long> {
}
