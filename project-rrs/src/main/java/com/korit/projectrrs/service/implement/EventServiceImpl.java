package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.common.constant.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.event.EventResponseAllDto;
import com.korit.projectrrs.dto.event.EventResponseDto;
import com.korit.projectrrs.entity.Event;
import com.korit.projectrrs.repositoiry.EventRepository;
import com.korit.projectrrs.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<List<EventResponseAllDto>> getAllEvents() {
        try {
            List<Event> events = eventRepository.findAll();
            List<EventResponseAllDto> responseDtos = events.stream()
                    .map(EventResponseAllDto::new)
                    .collect(Collectors.toList());

            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDtos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<EventResponseDto> getEventById(Long eventId) {
        try {
            Event event = eventRepository.findById(eventId)
                    .orElseThrow(() -> new RuntimeException(ResponseMessage.NOT_FOUND_EVENT));

            EventResponseDto responseDto = new EventResponseDto(event);

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
