package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.common.constant.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.announcement.AnnouncementResponseAllDto;
import com.korit.projectrrs.dto.announcement.AnnouncementResponseDto;
import com.korit.projectrrs.entity.Announcement;
import com.korit.projectrrs.repositoiry.AnnouncementRepository;
import com.korit.projectrrs.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<List<AnnouncementResponseAllDto>> getAllAnnouncements() {
        try {
            List<Announcement> announcements = announcementRepository.findAll();
            List<AnnouncementResponseAllDto> responseDtos = announcements.stream()
                    .map(AnnouncementResponseAllDto::new)
                    .collect(Collectors.toList());

            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDtos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<AnnouncementResponseDto> getAnnouncementById(Long announcementId) {
        try {
            Announcement announcement = announcementRepository.findById(announcementId)
                    .orElseThrow(() -> new RuntimeException(ResponseMessage.NOT_FOUND_ANNOUNCEMENT));

            AnnouncementResponseDto responseDto = new AnnouncementResponseDto(announcement);

            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseDto.setFailed(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }


}
