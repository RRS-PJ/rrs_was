package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.reservation.request.ReservationPostRequestDto;
import com.korit.projectrrs.dto.reservation.request.ReservationPutRequestDto;
import com.korit.projectrrs.dto.reservation.response.ReservationGetResponseDto;
import com.korit.projectrrs.dto.reservation.response.ReservationPostResponseDto;
import com.korit.projectrrs.dto.reservation.response.ReservationPutResponseDto;
import com.korit.projectrrs.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    @Override
    public ResponseDto<ReservationPostResponseDto> createReservation(Long userId, ReservationPostRequestDto dto) {
        return null;
    }

    @Override
    public ResponseDto<List<ReservationGetResponseDto>> getAllReservationByUserId(Long userId) {
        return null;
    }

    @Override
    public ResponseDto<ReservationGetResponseDto> getReservationByReservationId(Long userId) {
        return null;
    }

    @Override
    public ResponseDto<ReservationPutResponseDto> putReservationByReservationId(ReservationPutRequestDto dto) {
        return null;
    }

    @Override
    public ResponseDto<Void> deleteReview(Long reservationId) {
        return null;
    }
}
