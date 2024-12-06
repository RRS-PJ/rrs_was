package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.reservation.request.ReservationPostRequestDto;
import com.korit.projectrrs.dto.reservation.request.ReservationPutRequestDto;
import com.korit.projectrrs.dto.reservation.response.ReservationGetResponseDto;
import com.korit.projectrrs.dto.reservation.response.ReservationPostResponseDto;
import com.korit.projectrrs.dto.reservation.response.ReservationPutResponseDto;

import java.util.List;

public interface ReservationService {
    ResponseDto<ReservationPostResponseDto> createReservation(String userId, ReservationPostRequestDto dto);
    ResponseDto<List<ReservationGetResponseDto>> getAllReservationByUserId(String userId);
    ResponseDto<ReservationGetResponseDto> getReservationByReservationId(String userId);
    ResponseDto<ReservationPutResponseDto> putReservationByReservationId(ReservationPutRequestDto dto);
    ResponseDto<Void> deleteReview(Long reservationId);
}
