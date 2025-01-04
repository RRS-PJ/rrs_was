package com.korit.projectrrs.dto.event;

import com.korit.projectrrs.entity.Event;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class EventResponseDto {
    private Long eventId;
    private String eventTitle;
    private String eventContent;
    private LocalDateTime eventCreatedAt;

    public EventResponseDto(Event event) {
        this.eventId = event.getEventId();
        this.eventTitle = event.getEventTitle();
        this.eventContent = event.getEventContent();
        this.eventCreatedAt = event.getEventCreatedAt();
    }
}
