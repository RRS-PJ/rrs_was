package com.korit.projectrrs.repositoiry;

import com.korit.projectrrs.entity.HealthRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HealthRecordRepository extends JpaRepository<HealthRecord, Long> {

    Optional<HealthRecord> findByHealthRecordIdAndPet_PetIdAndPet_User_UserId(Long healthRecordId, Long petId, Long userId);

    List<HealthRecord> findAllByPet_User_UserIdAndPet_PetId(Long userId, Long petId);
}
