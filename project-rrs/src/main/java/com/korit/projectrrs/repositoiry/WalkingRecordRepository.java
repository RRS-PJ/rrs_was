package com.korit.projectrrs.repositoiry;

import com.korit.projectrrs.entity.WalkingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalkingRecordRepository extends JpaRepository<WalkingRecord, Long> {
}
