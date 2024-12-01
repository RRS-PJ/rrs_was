package com.korit.projectrrs.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "WalkingRecordsAttachments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class WalkingRecordAttachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long walkingRecordAttachmentId;

    @JoinColumn(name = "walkingRecordId", referencedColumnName = "walkingRecordId", nullable = false)
//    @ManyToOne(fetch = FetchType.LAZY)
    private long walkingRecordId;

    private String walkingRecordAttachmentFile;
}
