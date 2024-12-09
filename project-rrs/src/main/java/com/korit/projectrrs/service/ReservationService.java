package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.reservation.request.ReservationPostRequestDto;
import com.korit.projectrrs.dto.reservation.request.ReservationPutRequestDto;
import com.korit.projectrrs.dto.reservation.request.findProviderByDateRequestDto;
import com.korit.projectrrs.dto.reservation.request.getReservationByProviderIdRequestDto;
import com.korit.projectrrs.dto.reservation.response.*;

import java.util.List;

public interface ReservationService {
    ResponseDto<ReservationPostResponseDto> createReservation(Long userId, ReservationPostRequestDto dto);
    ResponseDto<List<ReservationGetResponseDto>> getAllReservationByUserId(Long userId);
    ResponseDto<ReservationGetResponseDto> getReservationByReservationId(Long userId);
    ResponseDto<ReservationPutResponseDto> putReservationByReservationId(ReservationPutRequestDto dto);
    ResponseDto<Void> deleteReview(Long reservationId);
    ResponseDto<findProviderByDateResponseDto> findProviderByDate(findProviderByDateRequestDto dto);
    ResponseDto<getReservationByProviderIdResponseDto> getReservationByProviderId(Long providerId, getReservationByProviderIdRequestDto dto);
}
