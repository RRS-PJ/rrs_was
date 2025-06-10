package com.korit.projectrrs.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "HEALTH_RECORD_ATTACHMENTS")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class HealthRecordAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HEALTH_RECORD_ATTACHMENT_ID", nullable = false, updatable = false)
    private Long attachmentId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "HEALTH_RECORD_ID", nullable = false)
    private HealthRecord healthRecord;

    @Column(name = "HEALTH_RECORD_ATTACHMENT_FILE", length = 255)
    private String healthRecordAttachmentFile;

}
