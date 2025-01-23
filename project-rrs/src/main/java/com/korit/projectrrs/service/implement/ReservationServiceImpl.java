package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.common.constant.ResponseMessage;
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
import java.util.*;
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
        LocalDate currentDate = LocalDate.now();
        LocalDate maxAllowedDate = currentDate.plusDays(30);
        String memo = dto.getReservationMemo();
        Long providerId = dto.getProviderId();

        if (startDate.isBefore(currentDate)) {
            return ResponseDto.setFailed(ResponseMessage.START_DATE_CANNOT_BE_IN_PAST);
        }

        if (startDate.isAfter(maxAllowedDate) || endDate.isAfter(maxAllowedDate)) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_DATE_TOO_LATE);
        }

        if (endDate.isBefore(startDate)) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_DATE_RANGE);
        }

        if (startDate.plusDays(7).isBefore(endDate)) {
            return ResponseDto.setFailed(ResponseMessage.DATE_RANGE_TOO_LONG);
        }

        if (startDate.isEqual(endDate)) {
            return ResponseDto.setFailed(ResponseMessage.MINIMUM_ONE_DAY_RESERVATION);
        }

        int isReserved = reservationRepository.existsByProviderAndDateRange(dto.getProviderId(), dto.getReservationStartDate(), dto.getReservationEndDate());
        if (isReserved == 1) {
            return ResponseDto.setFailed(ResponseMessage.RESERVATION_ALREADY_EXISTS);
        }

        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(()-> new InternalException(ResponseMessage.NOT_EXIST_USER_ID));

            User provider = userRepository.findProviderById(providerId)
                    .orElseThrow(()-> new InternalException(ResponseMessage.NOT_EXIST_PROVIDER_ID));

            Reservation newReservation = Reservation.builder()
                    .user(user)
                    .provider(provider)
                    .reservationStartDate(startDate)
                    .reservationEndDate(endDate)
                    .reservationStatus(ReservationStatus.PENDING)
                    .reservationMemo(memo)
                    .build();

            reservationRepository.save(newReservation);
            data = new CreateReservationResponseDto(newReservation, reviewRepository.findAvgReviewScoreByProvider(provider.getUserId())
                    .orElse(0.0)
            );
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<List<GetReservationResponseDto>> getAllReservationByUserId(Long userId) {
        List<GetReservationResponseDto> data = null;

        try {
            if (!userRepository.existsById(userId)) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);
            }

            List<Reservation> reservations = reservationRepository.findAllByUserId(userId);

            if(reservations.isEmpty()){
                return ResponseDto.setSuccess(ResponseMessage.SUCCESS, new ArrayList<>());
            }

            Set<Long> providerUserIds = reservations.stream()
                    .filter(reservation -> reservation.getProvider() != null)
                    .map(reservation -> reservation.getProvider().getUserId())
                    .collect(Collectors.toSet());

            List<Object[]> rawReviewScores = reviewRepository.findAverageReviewScoresByProviders(providerUserIds);

            Map<Long, Double> providerAverageReviewScores = rawReviewScores.stream()
                    .collect(Collectors.toMap(
                            row -> ((Long) row[0]).longValue(),
                            row -> (Double) row[1]
                    ));

            data = reservations.stream()
                    .map(reservation -> {
                        Long providerUserId = reservation.getProvider().getUserId();
                        Double averageReviewScore = Optional.ofNullable(providerAverageReviewScores.get(providerUserId)).orElse(0.0);
                        return new GetReservationResponseDto(reservation, averageReviewScore);
                    })
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<GetReservationResponseDto> getReservationByReservationId(Long reservationId) {
        GetReservationResponseDto data = null;

        try {
            Reservation reservation = reservationRepository.findById(reservationId)
                    .orElseThrow(() -> new InternalException(ResponseMessage.NOT_EXIST_RESERVATION));

            double avgReviewScore = reviewRepository.findAvgReviewScoreByProvider(reservation.getProvider().getUserId())
                    .orElse(0.0);

            data = new GetReservationResponseDto(reservation, avgReviewScore);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<UpdateReservationResponseDto> updateReservationByReservationId(Long reservationId, ReservationUpdateRequestDto dto) {
        UpdateReservationResponseDto data = null;
        String memo = dto.getReservationMemo();

        if (memo.length() > 1500){
            return ResponseDto.setFailed(ResponseMessage.RESERVATION_MEMO_TOO_LONG);
        }

        try {
            Reservation reservation = reservationRepository.findById(reservationId)
                    .orElseThrow(() -> new InternalException(ResponseMessage.NOT_EXIST_RESERVATION));

            if (reservation.getReservationStatus() != ReservationStatus.PENDING){
                return  ResponseDto.setFailed(ResponseMessage.INVALIDATED_RESERVATION_STATUS);
            }

            reservation = reservation.toBuilder()
                    .reservationMemo(memo)
                    .build();

            reservationRepository.save(reservation);
            data = new UpdateReservationResponseDto(reservation);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<Set<GetProviderByDateResponseDto>> findProviderByDate(Long userId, GetProviderByDateRequestDto dto) {
        Set<GetProviderByDateResponseDto> data = null;
        LocalDate startDate = dto.getStartDate();
        LocalDate endDate = dto.getEndDate();

        try {
            Set<Long> providerIds = reservationRepository.findProviderByDate(userId, startDate, endDate);

            if (providerIds.isEmpty()) {
                return ResponseDto.setSuccess(ResponseMessage.SUCCESS, Collections.emptySet());
            }

            data = providerIds.stream()
                    .map(providerId ->{
                        User provider = userRepository.findById(providerId)
                                .orElseThrow(() -> new InternalException(ResponseMessage.NOT_EXIST_PROVIDER_ID));
                        return new GetProviderByDateResponseDto(provider);
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

        try {
            Reservation updatedReservation = reservationRepository.findById(dto.getReservationId())
                    .orElseThrow(() -> new InternalException(ResponseMessage.NOT_EXIST_RESERVATION));

            updatedReservation = updatedReservation.toBuilder()
                    .reservationStatus(dto.getReservationStatus())
                    .build();
            reservationRepository.save(updatedReservation);

            data = new UpdateStatusResponseDto(updatedReservation.getReservationStatus());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<HasReview> hasReview(Long reservationId) {
        HasReview data = null;

        reservationRepository.findById(reservationId)
                .orElseThrow(() -> new InternalException(ResponseMessage.NOT_EXIST_RESERVATION));

            boolean result = reviewRepository.existsByReservation_ReservationId(reservationId);
            if(result){
                data = new HasReview('Y');
            } else {
                data = new HasReview('N');
            }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}