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
import java.util.ArrayList;
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
        LocalDate currentDate = LocalDate.now();
        LocalDate maxAllowedDate = currentDate.plusDays(30);  // 30일 이후의 날짜 제한
        String memo = dto.getReservationMemo();
        Long providerId = dto.getProviderId();

        // 시작 날짜가 과거일 경우
        if (startDate.isBefore(currentDate)) {
            return ResponseDto.setFailed(ResponseMessage.START_DATE_CANNOT_BE_IN_PAST);
        }

        // 시작 또는 종료 날짜가 30일 이상 초과한 경우
        if (startDate.isAfter(maxAllowedDate) || endDate.isAfter(maxAllowedDate)) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_DATE_TOO_LATE);
        }

        // 종료 날짜가 시작 날짜보다 이전인 경우
        if (endDate.isBefore(startDate)) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_DATE_RANGE);
        }

        // 예약 기간이 7일을 초과하는 경우
        if (startDate.plusDays(7).isBefore(endDate)) {
            return ResponseDto.setFailed(ResponseMessage.DATE_RANGE_TOO_LONG);
        }

        // 1일 이상의 예약이 아닌 경우
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
                // 유저 아이디가 존재하지 않음
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);
            }

            List<Reservation> reservations = reservationRepository.findAllByUserId(userId);

            // 예약 페이지가 없을 경우
            if(reservations.isEmpty()){
                return ResponseDto.setSuccess(ResponseMessage.SUCCESS, new ArrayList<>());
            }

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
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<List<GetByProviderIdResponseDto>> getReservationByProviderId(Long providerId) {
        List<GetByProviderIdResponseDto> data = null;

        try {
            // 댕시터가 있는지 확인
            if (!userRepository.existsById(providerId)) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_PROVIDER_ID);
            }

            List<Reservation> reservations = reservationRepository.findAllByProviderId(providerId);

            if (reservations.isEmpty()) {
                return ResponseDto.setSuccess(ResponseMessage.SUCCESS, new ArrayList<>());
            }

            data = reservations.stream()
                    .map(GetByProviderIdResponseDto::new)
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

        //유효성 검사
        if (memo.length() > 1500){
            return ResponseDto.setFailed(ResponseMessage.RESERVATION_MEMO_TOO_LONG);
        }

        try {
            Reservation reservation = reservationRepository.findById(reservationId)
                    .orElseThrow(() -> new InternalException(ResponseMessage.NOT_EXIST_RESERVATION));

            // 예약대기가 아니면 수정이 불가능
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
    public ResponseDto<Set<GetProviderByDateResponseDto>> findProviderByDate(GetProviderByDateRequestDto dto) {
        Set<GetProviderByDateResponseDto> data = null;
        LocalDate startDate = dto.getStartDate();
        LocalDate endDate = dto.getEndDate();

        try {
            Set<Long> providerIds = reservationRepository.findProviderByDate(startDate, endDate);

            if(providerIds.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_PROVIDER_ID);
            }
            data = providerIds.stream()
                    .map(providerId ->{
                        User provider = userRepository.findById(providerId)
                                .orElseThrow(() -> new InternalException(ResponseMessage.NOT_EXIST_PROVIDER_ID));
                        double avgScore = reviewRepository.findAvgReviewScoreByProvider(providerId)
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

        try {
            Reservation cancledReservation = reservationRepository.findById(dto.getReservationId())
                    .orElseThrow(() -> new InternalException(ResponseMessage.NOT_EXIST_RESERVATION));

            cancledReservation = cancledReservation.toBuilder()
                    .reservationStatus(dto.getReservationStatus())
                    .build();
            reservationRepository.save(cancledReservation);

            data = new UpdateStatusResponseDto(cancledReservation.getReservationStatus());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}