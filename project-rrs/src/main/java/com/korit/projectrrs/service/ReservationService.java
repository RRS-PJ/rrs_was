package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.reservation.request.ReservationPostRequestDto;
import com.korit.projectrrs.dto.reservation.request.ReservationPutRequestDto;
import com.korit.projectrrs.dto.reservation.request.FindProviderByDateRequestDto;
import com.korit.projectrrs.dto.reservation.request.GetReservationByProviderIdRequestDto;
import com.korit.projectrrs.dto.reservation.response.*;

import java.util.List;
import java.util.Set;

public interface ReservationService {
    ResponseDto<ReservationPostResponseDto> createReservation(Long userId, ReservationPostRequestDto dto);
    ResponseDto<List<ReservationGetResponseDto>> getAllReservationByUserId(Long userId);
    ResponseDto<ReservationGetResponseDto> getReservationByReservationId(Long userId);
    ResponseDto<ReservationPutResponseDto> putReservationByReservationId(ReservationPutRequestDto dto);
    ResponseDto<Void> deleteReview(Long reservationId);
    ResponseDto<Set<FindProviderByDateResponseDto>> findProviderByDate(FindProviderByDateRequestDto dto);
    ResponseDto<List<getReservationByProviderIdResponseDto>> getReservationByProviderId(Long providerId, GetReservationByProviderIdRequestDto dto);
}
