package com.korit.projectrrs.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "COMMUNITY_ATTACHMENTS")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CommunityAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMUNITY_ATTACHMENT_ID", nullable = false, updatable = false)
    private Long communityAttachmentId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "COMMUNITY_ID", nullable = false)
    private Community community;

    @Column(name = "COMMUNITY_ATTACHMENT_FILE", nullable = false, length = 255)
    private String communityAttachment;

}