package com.korit.projectrrs.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "walkingRecordAttachments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class WalkingRecordAttachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long walkingRecordAttachmentId;

    @JoinColumn(name = "walkingRecordId", referencedColumnName = "walkingRecordId", nullable = false)
//    @ManyToOne(fetch = FetchType.LAZY)
    private Long walkingRecordId;

    private String walkingRecordAttachmentFile;
}
