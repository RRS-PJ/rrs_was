package com.korit.projectrrs.repositoiry;

import com.korit.projectrrs.entity.PetProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetProfileRepository extends JpaRepository<PetProfile, Long> {
    @Query("SELECT p " +
            "FROM PetProfile p " +
            "WHERE p.user.userId = :userId " +
            "AND p.petProfileId = :petProfileId")
    Optional<PetProfile> findByUserIdAndPetProfileId(@Param("userId") String userId, @Param("petProfileId") Long petProfileId);


//    @Query("SELECT p " +
//            "FROM PetProfile p " +
//            "WHERE p.user.userId = :userId ")
//    Optional<PetProfile> findByPetProfileId(@Param("userId") String userId);
}
