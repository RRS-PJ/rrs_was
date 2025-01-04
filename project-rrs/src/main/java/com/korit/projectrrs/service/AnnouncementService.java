package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.announcement.AnnouncementResponseAllDto;
import com.korit.projectrrs.dto.announcement.AnnouncementResponseDto;

import java.util.List;

public interface AnnouncementService {
    ResponseDto<List<AnnouncementResponseAllDto>> getAllAnnouncements();
    ResponseDto<AnnouncementResponseDto> getAnnouncementById(Long announcementId);
}
