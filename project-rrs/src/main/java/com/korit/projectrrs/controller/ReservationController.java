package com.korit.projectrrs.controller;

import com.korit.projectrrs.common.ApiMappingPattern;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.reservation.request.*;
import com.korit.projectrrs.dto.reservation.response.*;
import com.korit.projectrrs.security.PrincipalUser;
import com.korit.projectrrs.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiMappingPattern.RESERVATION)
public class ReservationController {
    private final ReservationService reservationService;

    private final String RESERVATION_GET = "/{reservationId}";
    private final String RESERVATION_PUT = "/{reservationId}";

    @PostMapping
    private ResponseEntity<ResponseDto<CreateReservationResponseDto>> createReservation (
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestBody CreateReservationRequestDto dto
            ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<CreateReservationResponseDto> response = reservationService.createReservation(userId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping
    private ResponseEntity<ResponseDto<List<GetReservationResponseDto>>> getAllReservationByUserId (
            @AuthenticationPrincipal PrincipalUser principalUser
            ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<List<GetReservationResponseDto>> response = reservationService.getAllReservationByUserId(userId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping()
    private ResponseEntity<ResponseDto<GetReservationResponseDto>> getReservationByReservationId (
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestParam Long reservationId
    ) {
        ResponseDto<GetReservationResponseDto> response = reservationService.getReservationByReservationId(reservationId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping
    private ResponseEntity<ResponseDto<UpdateReservationResponseDto>> updateReservation (
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestBody ReservationUpdateRequestDto dto
        ){
        ResponseDto<UpdateReservationResponseDto> response = reservationService.putReservationByReservationId(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @PostMapping
    private ResponseEntity<ResponseDto<Set<GetProviderByDateResponseDto>>> findProviderByDate (
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestBody GetProviderByDateRequestDto dto
    ) {
        ResponseDto<Set<GetProviderByDateResponseDto>> response = reservationService.findProviderByDate(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping
    private ResponseEntity<ResponseDto<List<GetByProviderResponseDto>>> getAllReservationByProviderId (
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestBody GetByProviderRequestDto dto
    ) {
        Long providerId = principalUser.getUser().getUserId();
        ResponseDto<List<GetByProviderResponseDto>> response = reservationService.getReservationByProviderId(providerId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping
    private ResponseEntity<ResponseDto<UpdateStatusResponseDto>> updateReservationStatus (
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestBody UpdateStatusRequestDto dto
    ){
        ResponseDto<UpdateStatusResponseDto> response = reservationService.updateReservationStatus(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }
}