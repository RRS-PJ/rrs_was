package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.reservation.request.*;
import com.korit.projectrrs.dto.reservation.response.*;

import java.util.List;
import java.util.Set;

public interface ReservationService {
    ResponseDto<CreateReservationResponseDto> createReservation(Long userId, CreateReservationRequestDto dto);
    ResponseDto<List<GetReservationResponseDto>> getAllReservationByUserId(Long userId);
    ResponseDto<List<GetByProviderResponseDto>> getReservationByProviderId(Long providerId, GetByProviderRequestDto dto);
    ResponseDto<GetReservationResponseDto> getReservationByReservationId(Long userId);
    ResponseDto<UpdateReservationResponseDto> putReservationByReservationId(ReservationUpdateRequestDto dto);
    ResponseDto<Set<GetProviderByDateResponseDto>> findProviderByDate(GetProviderByDateRequestDto dto);
    ResponseDto<UpdateStatusResponseDto> updateReservationStatus(UpdateStatusRequestDto dto);
}