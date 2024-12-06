package com.korit.projectrrs.repositoiry;

import com.korit.projectrrs.entity.CustomerSupport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerSupportRepository extends JpaRepository<CustomerSupport, Long> {
}
