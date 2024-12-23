package com.korit.projectrrs.dto.announcement;

import com.korit.projectrrs.entity.Announcement;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AnnouncementResponseDto {
    private Long announcementId;
    private String announcementTitle;
    private String announcementContent;
    private LocalDateTime announcementCreatedAt;

    public AnnouncementResponseDto(Announcement announcement) {
        this.announcementId = announcement.getAnnouncementId();
        this.announcementTitle = announcement.getAnnouncementTitle();
        this.announcementContent = announcement.getAnnouncementContent();
        this.announcementCreatedAt = announcement.getAnnouncementCreatedAt();
    }
}
