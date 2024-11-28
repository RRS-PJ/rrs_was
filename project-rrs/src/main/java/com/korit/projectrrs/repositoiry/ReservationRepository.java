package com.korit.projectrrs.repositoiry;

import com.korit.projectrrs.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
