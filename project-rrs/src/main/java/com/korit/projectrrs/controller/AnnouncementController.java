package com.korit.projectrrs.controller;

import com.korit.projectrrs.common.constant.ApiMappingPattern;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.announcement.AnnouncementResponseAllDto;
import com.korit.projectrrs.dto.announcement.AnnouncementResponseDto;
import com.korit.projectrrs.service.AnnouncementService;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.korit.projectrrs.common.constant.ApiMappingPattern.ANNOUNCEMENT_BY_ID;

@RestController
@RequestMapping(ApiMappingPattern.ANNOUNCEMENT)
@RequiredArgsConstructor
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @GetMapping
    @PermitAll
    public ResponseEntity<ResponseDto<List<AnnouncementResponseAllDto>>> getAllAnnouncements() {
        ResponseDto<List<AnnouncementResponseAllDto>> response = announcementService.getAllAnnouncements();
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NO_CONTENT;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(ANNOUNCEMENT_BY_ID)
    @PermitAll
    public ResponseEntity<ResponseDto<AnnouncementResponseDto>> getAnnouncementById(@PathVariable("announcementId") Long announcementId) {
        ResponseDto<AnnouncementResponseDto> response = announcementService.getAnnouncementById(announcementId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }
}
