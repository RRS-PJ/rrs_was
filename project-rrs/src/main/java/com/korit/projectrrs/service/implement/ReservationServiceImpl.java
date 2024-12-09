package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.reservation.request.ReservationPostRequestDto;
import com.korit.projectrrs.dto.reservation.request.ReservationPutRequestDto;
import com.korit.projectrrs.dto.reservation.request.findProviderByDateRequestDto;
import com.korit.projectrrs.dto.reservation.request.getReservationByProviderIdRequestDto;
import com.korit.projectrrs.dto.reservation.response.*;
import com.korit.projectrrs.repositoiry.ReservationRepository;
import com.korit.projectrrs.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

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

    @Override
    public ResponseDto<findProviderByDateResponseDto> findProviderByDate(findProviderByDateRequestDto dto) {
        ResponseDto<findProviderByDateResponseDto> data = null;
        LocalDate startDate = dto.getStartDate();
        LocalDate endDate = dto.getEndDate();
        List<Long> providersId = reservationRepository.findProviderByDate(startDate, endDate);
        return null;
    }

    @Override
    public ResponseDto<getReservationByProviderIdResponseDto> getReservationByProviderId(Long providerId, getReservationByProviderIdRequestDto dto) {
        return null;
    }
}
