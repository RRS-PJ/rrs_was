package com.korit.projectrrs.repositoiry;

import com.korit.projectrrs.entity.WalkingRecordAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalkingRecordAttachmentRepository extends JpaRepository<WalkingRecordAttachment, Long> {
}
