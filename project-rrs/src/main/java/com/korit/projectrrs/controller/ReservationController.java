package com.korit.projectrrs.controller;

import com.korit.projectrrs.common.constant.ApiMappingPattern;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.reservation.request.*;
import com.korit.projectrrs.dto.reservation.response.*;
import com.korit.projectrrs.security.PrincipalUser;
import com.korit.projectrrs.service.ReservationService;
import jakarta.validation.Valid;
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
    private final String RESERVATION_GET_MINE_USER = "/users/me";
    private final String RESERVATION_PUT = "/{reservationId}";
    private final String RESERVATION_STATUS = "/status";
    private final String FIND_PROVIDER_BY_DATE = "/available-providers";
    private final String HAS_REVIEW = "/{reservationId}/review";

    @PostMapping
    private ResponseEntity<ResponseDto<CreateReservationResponseDto>> createReservation (
            @AuthenticationPrincipal PrincipalUser principalUser,
            @Valid @RequestBody CreateReservationRequestDto dto
            ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<CreateReservationResponseDto> response = reservationService.createReservation(userId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(RESERVATION_GET_MINE_USER)
    private ResponseEntity<ResponseDto<List<GetReservationResponseDto>>> getAllReservationByUserId (
            @AuthenticationPrincipal PrincipalUser principalUser
            ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<List<GetReservationResponseDto>> response = reservationService.getAllReservationByUserId(userId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(RESERVATION_GET)
    private ResponseEntity<ResponseDto<GetReservationResponseDto>> getReservationByReservationId (
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long reservationId
    ) {
        ResponseDto<GetReservationResponseDto> response = reservationService.getReservationByReservationId(reservationId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping(RESERVATION_PUT)
    private ResponseEntity<ResponseDto<UpdateReservationResponseDto>> updateReservation (
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long reservationId,
            @Valid @RequestBody ReservationUpdateRequestDto dto
        ){
        ResponseDto<UpdateReservationResponseDto> response = reservationService.updateReservationByReservationId(reservationId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @PostMapping(FIND_PROVIDER_BY_DATE)
    private ResponseEntity<ResponseDto<Set<GetProviderByDateResponseDto>>> findProviderByDate (
            @AuthenticationPrincipal PrincipalUser principalUser,
            @Valid @RequestBody GetProviderByDateRequestDto dto
    ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<Set<GetProviderByDateResponseDto>> response = reservationService.findProviderByDate(userId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping(RESERVATION_STATUS)
    private ResponseEntity<ResponseDto<UpdateStatusResponseDto>> updateReservationStatus (
            @AuthenticationPrincipal PrincipalUser principalUser,
            @Valid @RequestBody UpdateStatusRequestDto dto
    ){
        ResponseDto<UpdateStatusResponseDto> response = reservationService.updateReservationStatus(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(HAS_REVIEW)
    private ResponseEntity<ResponseDto<HasReview>> hasReview (
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long reservationId
    ){
        ResponseDto<HasReview> response = reservationService.hasReview(reservationId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }
}