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

    // 파일 경로를 저장하는 필드. 서비스 코드와 일관되게 필드명을 수정함.
    @Column(name = "HEALTH_RECORD_ATTACHMENT_FILE", nullable = false, length = 255)
    private String healthRecordAttachmentFile;
}
