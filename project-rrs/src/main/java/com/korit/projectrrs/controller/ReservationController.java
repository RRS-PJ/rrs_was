package com.korit.projectrrs.controller;

import com.korit.projectrrs.common.ApiMappingPattern;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.reservation.request.ReservationPostRequestDto;
import com.korit.projectrrs.dto.reservation.request.ReservationPutRequestDto;
import com.korit.projectrrs.dto.reservation.request.FindProviderByDateRequestDto;
import com.korit.projectrrs.dto.reservation.request.GetReservationByProviderIdRequestDto;
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
    private final String RESERVATION_DELETE = "/{reservationId}";

    @PostMapping
    private ResponseEntity<ResponseDto<ReservationPostResponseDto>> createReservation (
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestBody ReservationPostRequestDto dto
            ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<ReservationPostResponseDto> response = reservationService.createReservation(userId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping
    private ResponseEntity<ResponseDto<List<ReservationGetResponseDto>>> getAllReservationByUserId (
            @AuthenticationPrincipal PrincipalUser principalUser
            ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<List<ReservationGetResponseDto>> response = reservationService.getAllReservationByUserId(userId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping()
    private ResponseEntity<ResponseDto<ReservationGetResponseDto>> getReservationByReservationId (
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestParam Long reservationId
    ) {
        ResponseDto<ReservationGetResponseDto> response = reservationService.getReservationByReservationId(reservationId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping
    private ResponseEntity<ResponseDto<ReservationPutResponseDto>> updateReservation (
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestBody ReservationPutRequestDto dto
        ){
        ResponseDto<ReservationPutResponseDto> response = reservationService.putReservationByReservationId(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping(RESERVATION_DELETE)
    private ResponseEntity<ResponseDto<Void>> deleteReservation (
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long reservationId
    ){
        ResponseDto<Void> response = reservationService.deleteReview(reservationId);
        HttpStatus status = response.isResult() ? HttpStatus.NO_CONTENT : HttpStatus.FORBIDDEN;
        return ResponseEntity.status(status).body(null);
    }

    @PostMapping
    private ResponseEntity<ResponseDto<Set<FindProviderByDateResponseDto>>> findProviderByDate (
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestBody FindProviderByDateRequestDto dto
    ) {
        ResponseDto<Set<FindProviderByDateResponseDto>> response = reservationService.findProviderByDate(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping
    private ResponseEntity<ResponseDto<List<getReservationByProviderIdResponseDto>>> getAllReservationByProviderId (
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestBody GetReservationByProviderIdRequestDto dto
    ) {
        Long providerId = principalUser.getUser().getUserId();
        ResponseDto<List<getReservationByProviderIdResponseDto>> response = reservationService.getReservationByProviderId(providerId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }
}