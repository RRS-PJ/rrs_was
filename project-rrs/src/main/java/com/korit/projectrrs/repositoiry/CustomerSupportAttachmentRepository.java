package com.korit.projectrrs.repositoiry;

import com.korit.projectrrs.entity.CustomerSupportAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerSupportAttachmentRepository extends JpaRepository<CustomerSupportAttachment, Long> {
    @Query(value = """
SELECT 
    *
FROM
    CUSTOMER_SUPPORT_ATTACHMENTS csa
WHERE
    csa.CUSTOMER_SUPPORT_ID = :csId
""", nativeQuery = true)
    List<CustomerSupportAttachment> findByCSId(@Param("csId")Long csId);
}
