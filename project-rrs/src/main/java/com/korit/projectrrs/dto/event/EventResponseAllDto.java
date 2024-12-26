package com.korit.projectrrs.dto.event;

import com.korit.projectrrs.entity.Event;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class EventResponseAllDto {
    private Long eventId;
    private String eventTitle;
    private LocalDateTime eventCreatedAt;

    public EventResponseAllDto(Event event) {
        this.eventId = event.getEventId();
        this.eventTitle = event.getEventTitle();
        this.eventCreatedAt = event.getEventCreatedAt();
    }
}
