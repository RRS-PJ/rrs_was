package com.korit.projectrrs.repositoiry;

import com.korit.projectrrs.entity.PetProfile;
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
            "WHERE w.petProfile.user.userId = :userId " +
            "AND w.petProfile.petProfileId = :petProfileId " +
            "AND w.walkingRecordId = :walkingRecordId")
    Optional<WalkingRecord> findWalkingRecordBytProfileIdAndWalkingRecordId(@Param("userId") String userId, @Param("petProfileId") long petProfileId, @Param("walkingRecordId") long walkingRecordId);

    @Query("SELECT w " +
            "FROM WalkingRecord w " +
            "WHERE w.petProfile.petProfileId = :petProfileId " +
            "AND w.walkingRecordCreateAt = :walkingRecordCreateAt")
    List<WalkingRecord> findAllWalkingReccrdByCreateAt(@Param("petProfileId") long petProfileId, @Param("walkingRecordCreateAt") LocalDate walkingRecordCreateAt);
}

