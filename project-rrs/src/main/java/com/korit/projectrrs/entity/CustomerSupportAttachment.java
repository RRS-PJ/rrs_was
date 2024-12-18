package com.korit.projectrrs.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CUSTOMER_SUPPORT_ATTACHMENTS")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSupportAttachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_SUPPORT_ATTACHMENT_ID", nullable = false, unique = true)
    private Long customerSupportAttachmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_SUPPORT_ID", nullable = false)
    @JsonBackReference
    private CustomerSupport customerSupport;

    @Column(name = "CUSTOMER_SUPPORT_ATTACHMENT_FILE", nullable = false)
    private String customerAttachmentFile;
}