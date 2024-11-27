package com.korit.projectrrs.repositoiry;

import com.korit.projectrrs.entity.CustomerSupportAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerSupportAttachmentRepository extends JpaRepository<CustomerSupportAttachment, Long> {
}