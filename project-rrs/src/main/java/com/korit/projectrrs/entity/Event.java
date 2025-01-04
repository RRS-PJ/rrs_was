package com.korit.projectrrs.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "EVENTS")
@Data
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EVENT_ID")
    private Long eventId;

    @Column(name = "EVENT_TITLE", nullable = false)
    private String eventTitle;

    @Column(name = "EVENT_CONTENT", nullable = false)
    private String eventContent;

    @Column(name = "EVENT_CREATE_AT", nullable = false)
    private LocalDateTime eventCreatedAt;

    @PrePersist
    protected void onCreate() {
        this.eventCreatedAt = LocalDateTime.now();
    }
}
