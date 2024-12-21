package com.korit.projectrrs.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "ANNOUNCEMENTS")
@Data
@NoArgsConstructor
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANNOUNCEMENT_ID")
    private Long announcementId;

    @Column(name = "ANNOUNCEMENT_TITLE", nullable = false)
    private String announcementTitle;

    @Column(name = "ANNOUNCEMENT_CONTENT", nullable = false)
    private String announcementContent;

    @Column(name = "ANNOUNCEMENT_CREATE_AT", nullable = false)
    private LocalDateTime announcementCreatedAt;

    @PrePersist
    protected void onCreate() { this.announcementCreatedAt = LocalDateTime.now(); }
}
