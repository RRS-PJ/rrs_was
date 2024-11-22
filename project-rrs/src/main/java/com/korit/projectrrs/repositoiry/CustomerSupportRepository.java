package com.korit.projectrrs.repositoiry;

import com.korit.projectrrs.entity.CustomerSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerSupportRepository extends JpaRepository <CustomerSupport, Long>{
    @Query("SELECT c " +
            "FROM CustomerSupport c " +
            "WHERE c.user.userId = :userId ")
    Optional<CustomerSupport> findByUserId(String userId);
}
