package com.korit.projectrrs.repositoiry;

import com.korit.projectrrs.entity.CustomerSupport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerSupportRepository extends JpaRepository<CustomerSupport, Long> {
    @Query(value = """
SELECT 
    *
FROM 
    CUSTOMER_SUPPORTS cs
WHERE
    cs.USER_ID = :userId
""", nativeQuery = true)
    List<CustomerSupport> findAllByUserId(@Param("userId") Long userId);
}