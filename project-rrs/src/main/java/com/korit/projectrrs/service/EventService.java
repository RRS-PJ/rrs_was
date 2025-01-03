package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.event.EventResponseAllDto;
import com.korit.projectrrs.dto.event.EventResponseDto;

import java.util.List;

public interface EventService {
    ResponseDto<List<EventResponseAllDto>> getAllEvents();
    ResponseDto<EventResponseDto> getEventById(Long eventId);
}
