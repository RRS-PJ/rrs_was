package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.common.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.reservation.request.ReservationPostRequestDto;
import com.korit.projectrrs.dto.reservation.request.ReservationPutRequestDto;
import com.korit.projectrrs.dto.reservation.request.FindProviderByDateRequestDto;
import com.korit.projectrrs.dto.reservation.request.GetReservationByProviderIdRequestDto;
import com.korit.projectrrs.dto.reservation.response.*;
import com.korit.projectrrs.entity.User;
import com.korit.projectrrs.repositoiry.ReservationRepository;
import com.korit.projectrrs.repositoiry.ReviewRepository;
import com.korit.projectrrs.repositoiry.UserRepository;
import com.korit.projectrrs.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.InternalException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

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
    public ResponseDto<Set<FindProviderByDateResponseDto>> findProviderByDate(FindProviderByDateRequestDto dto) {
        Set<FindProviderByDateResponseDto> data = null;
        LocalDate startDate = dto.getStartDate();
        LocalDate endDate = dto.getEndDate();
        try {
            Set<Long> providerIds = reservationRepository.findProviderByDate(startDate, endDate);
            // 댕시터의 유무 판별
            if(providerIds.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_PROVIDER_ID);
            }
            data = providerIds.stream()
                    .map(providerId ->{
                        User provider = userRepository.findById(providerId)
                                .orElseThrow(() -> new InternalException(ResponseMessage.NOT_EXIST_PROVIDER_ID));
                        double avgScore = reviewRepository.findAverageReviewScoreByProvider(providerId)
                                .orElse(0.0);
                        return new FindProviderByDateResponseDto(provider, avgScore);
                    })
                    .collect(Collectors.toSet());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<List<getReservationByProviderIdResponseDto>> getReservationByProviderId(Long providerId, GetReservationByProviderIdRequestDto dto) {
        return null;
    }
}
