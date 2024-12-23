package com.korit.projectrrs.repositoiry;

import com.korit.projectrrs.entity.WalkingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface WalkingRecordRepository extends JpaRepository<WalkingRecord, Long> {
    @Query("SELECT w " +
            "FROM WalkingRecord w " +
            "WHERE w.pet.user.userId = :userId " +
            "AND w.pet.petId = :petId " +
            "AND w.walkingRecordId = :walkingRecordId")
    Optional<WalkingRecord> findWalkingRecordBytPetIdAndWalkingRecordId(@Param("userId") Long userId, @Param("petId") Long petId, @Param("walkingRecordId") Long walkingRecordId);

    @Query("SELECT w " +
            "FROM WalkingRecord w " +
            "WHERE w.pet.petId = :petId " +
            "AND w.walkingRecordCreateAt = :walkingRecordCreateAt")
    List<WalkingRecord> findAllWalkingReccrdByCreateAt(@Param("petId") Long petId, @Param("walkingRecordCreateAt") LocalDate walkingRecordCreateAt);
}

