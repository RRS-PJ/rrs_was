package com.korit.projectrrs.repositoiry;

import com.korit.projectrrs.entity.WalkingRecordAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WalkingRecordAttachmentRepository extends JpaRepository<WalkingRecordAttachment, Long> {
    @Query("SELECT wra " +
            "FROM WalkingRecordAttachment wra " +
            "WHERE wra.walkingRecord.pet.petId = :petId " +
            "AND wra.walkingRecord.pet.user.userId = :userId " +
            "AND wra.walkingRecord.walkingRecordId = :wrId")
    List<WalkingRecordAttachment> findByWRId(@Param("userId") Long userId,
                                             @Param("petId") Long petId,
                                             @Param("wrId") Long wrId);
}