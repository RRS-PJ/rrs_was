package com.korit.projectrrs.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CUSTOMER_SUPPORTS_ATTACHMENT")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSupportAttachment {
    @Id
    @Column(name = "CUSTOMER_SUPPORT_ATTACHMENT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerSupportAttachmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_SUPPORT_ID", nullable = false)
    private CustomerSupport customerSupport;

    @Column(name = "CUSTOMER_SUPPORT_ATTACHMENT_FILE")
    private String customerSupportAttachmentFile;
}