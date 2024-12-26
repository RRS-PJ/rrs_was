package com.korit.projectrrs.controller;

import com.korit.projectrrs.common.ApiMappingPattern;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.event.EventResponseAllDto;
import com.korit.projectrrs.dto.event.EventResponseDto;
import com.korit.projectrrs.service.EventService;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.korit.projectrrs.common.ApiMappingPattern.EVENT_BY_ID;

@RestController
@RequestMapping(ApiMappingPattern.EVENT)
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    @PermitAll
    public ResponseEntity<ResponseDto<List<EventResponseAllDto>>> getAllEvents() {
        ResponseDto<List<EventResponseAllDto>> response = eventService.getAllEvents();
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NO_CONTENT;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(EVENT_BY_ID)
    @PermitAll
    public ResponseEntity<ResponseDto<EventResponseDto>> getEventById(@PathVariable("eventId") Long eventId) {
        ResponseDto<EventResponseDto> response = eventService.getEventById(eventId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }
}
