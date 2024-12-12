package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.common.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.reservation.request.*;
import com.korit.projectrrs.dto.reservation.response.*;
import com.korit.projectrrs.entity.Reservation;
import com.korit.projectrrs.entity.ReservationStatus;
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
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public ResponseDto<CreateReservationResponseDto> createReservation(Long userId, CreateReservationRequestDto dto) {
        CreateReservationResponseDto data = null;
        LocalDate startDate = dto.getReservationStartDate();
        LocalDate endDate = dto.getReservationEndDate();

        // 유저가 있는지 확인
        User user = userRepository.findById(userId)
                        .orElseThrow(()-> new InternalException(ResponseMessage.NOT_EXIST_USER_ID));
        // 댕시터가 있는지 확인
        User provider = userRepository.findProviderById(dto.getProviderId())
                .orElseThrow(()-> new InternalException(ResponseMessage.NOT_EXIST_PROVIDER_ID));

        Reservation newReservation = Reservation.builder()
                .user(user)
                .provider(provider)
                .reservationStartDate(startDate)
                .reservationEndDate(endDate)
                .reservationStatus(ReservationStatus.PENDING)
                .build();

        reservationRepository.save(newReservation);
        data = new CreateReservationResponseDto(newReservation, reviewRepository.findAverageReviewScoreByProvider(provider.getUserId())
                .orElse(0.0)
                );

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<List<GetReservationResponseDto>> getAllReservationByUserId(Long userId) {
        List<GetReservationResponseDto> data = null;
        List<Reservation> reservations = reservationRepository.findAllByUserId(userId);

        Map<Long, Double> providerAverageReviewScores = reviewRepository.findAverageReviewScoresByProviders(
                reservations.stream()
                        .map(reservation -> reservation.getProvider().getUserId())
                        .collect(Collectors.toSet())
        );

        data = reservations.stream()
                .map(reservation -> {
                    Long providerUserId = reservation.getProvider().getUserId();
                    Double averageReviewScore = providerAverageReviewScores.get(providerUserId);
                    return new GetReservationResponseDto(reservation, averageReviewScore);
                })
                .collect(Collectors.toList());

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<List<GetByProviderResponseDto>> getReservationByProviderId(Long providerId, GetByProviderRequestDto dto) {
        List<GetByProviderResponseDto> data = null;
        List<Reservation> reservations = reservationRepository.findAllByProviderId(providerId);

        data = reservations.stream()
                .map(GetByProviderResponseDto::new)
                .collect(Collectors.toList());

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<GetReservationResponseDto> getReservationByReservationId(Long reservationId) {
        GetReservationResponseDto data = null;
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new InternalException(ResponseMessage.NOT_EXIST_RESERVATION));

        double avgReviewScore = reviewRepository.findAverageReviewScoreByProvider(reservation.getProvider().getUserId())
                .orElse(0.0);

        data = new GetReservationResponseDto(reservation, avgReviewScore);

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<UpdateReservationResponseDto> putReservationByReservationId(ReservationUpdateRequestDto dto) {
        UpdateReservationResponseDto data = null;
        Long reservationId = dto.getReservationId();
        String memo = dto.getReservationMemo();
        Reservation reservation = reservationRepository.findById(reservationId)
                    .orElseThrow(() -> new InternalException(ResponseMessage.NOT_EXIST_RESERVATION));
        reservation = reservation.toBuilder()
                .reservationMemo(memo)
                .build();

        reservationRepository.save(reservation);
        data = new UpdateReservationResponseDto(reservation);
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<Set<GetProviderByDateResponseDto>> findProviderByDate(GetProviderByDateRequestDto dto) {
        Set<GetProviderByDateResponseDto> data = null;
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
                        return new GetProviderByDateResponseDto(provider, avgScore);
                    })
                    .collect(Collectors.toSet());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<UpdateStatusResponseDto> updateReservationStatus(UpdateStatusRequestDto dto) {
        UpdateStatusResponseDto data = null;
        Reservation cancledReservation = reservationRepository.findById(dto.getReservationId())
                .orElseThrow(() -> new InternalException(ResponseMessage.NOT_EXIST_RESERVATION));
        cancledReservation = cancledReservation.toBuilder()
                .reservationStatus(dto.getReservationStatus())
                .build();
        reservationRepository.save(cancledReservation);

        data = new UpdateStatusResponseDto(cancledReservation.getReservationStatus());

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
