package com.korit.projectrrs.controller;

import com.korit.projectrrs.common.ApiMappingPattern;
import com.korit.projectrrs.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiMappingPattern.RESERVATION)
public class ReservationController {
    private final ReservationService reservationService;

    private final String RESERVATION_GET = "/{reservationId}";
    private final String RESERVATION_PUT = "/{reservationId}";
    private final String RESERVATION_DELETE = "/{reservationId}";
}
