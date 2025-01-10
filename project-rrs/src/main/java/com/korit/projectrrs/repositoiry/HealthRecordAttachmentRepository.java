package com.korit.projectrrs.repositoiry;

import com.korit.projectrrs.entity.HealthRecordAttachment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealthRecordAttachmentRepository extends JpaRepository<HealthRecordAttachment, Long> {
    @Query("SELECT hra FROM HealthRecordAttachment hra WHERE hra.healthRecord.healthRecordId = :healthRecordId")
    List<HealthRecordAttachment> findByHealthRecordId(@Param("healthRecordId") Long healthRecordId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM HEALTH_RECORD_ATTACHMENTS WHERE HEALTH_RECORD_ID = :healthRecordId", nativeQuery = true)
    void deleteAll(@Param("healthRecordId") Long healthRecordId);
}
