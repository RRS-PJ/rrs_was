package com.korit.projectrrs.repositoiry;

import com.korit.projectrrs.entity.Pet;
<<<<<<< HEAD
import com.korit.projectrrs.security.PrincipalUser;
=======
>>>>>>> develop
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    @Query("SELECT p " +
            "FROM Pet p " +
            "WHERE p.user.userId = :userId " +
            "AND p.petId = :petId")
    Optional<Pet> findPetByUserId(@Param("userId") Long userId, @Param("petId") Long petId);


    @Query("SELECT p " +
            "FROM Pet p " +
            "WHERE p.user.userId = :userId ")
    List<Pet> findAllPetByUserId(@Param("userId") Long userId);
<<<<<<< HEAD
}
=======
}
>>>>>>> develop
