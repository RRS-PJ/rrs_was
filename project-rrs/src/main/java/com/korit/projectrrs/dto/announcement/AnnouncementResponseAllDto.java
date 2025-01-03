package com.korit.projectrrs.dto.announcement;

import com.korit.projectrrs.entity.Announcement;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AnnouncementResponseAllDto {
    private Long announcementId;
    private String announcementTitle;
    private LocalDateTime announcementCreatedAt;

    public AnnouncementResponseAllDto(Announcement announcement) {
        this.announcementId = announcement.getAnnouncementId();
        this.announcementTitle = announcement.getAnnouncementTitle();
        this.announcementCreatedAt = announcement.getAnnouncementCreatedAt();
    }
}
