package com.korit.projectrrs.repositoiry;

import com.korit.projectrrs.entity.HealthRecordAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealthRecordAttachmentRepository extends JpaRepository<HealthRecordAttachment, Long> {
    List<HealthRecordAttachment> findByHealthRecord_HealthRecordId(Long healthRecordId);
}
