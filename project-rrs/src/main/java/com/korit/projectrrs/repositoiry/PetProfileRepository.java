package com.korit.projectrrs.repositoiry;

import com.korit.projectrrs.entity.PetProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetProfileRepository extends JpaRepository<PetProfile, Long> {
    Optional<PetProfile> findByPetProfileId(Long PetProfileId);
}
