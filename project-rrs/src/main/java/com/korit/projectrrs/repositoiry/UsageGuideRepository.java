package com.korit.projectrrs.repositoiry;

import com.korit.projectrrs.entity.UsageGuide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsageGuideRepository extends JpaRepository<UsageGuide, Long> {

}
