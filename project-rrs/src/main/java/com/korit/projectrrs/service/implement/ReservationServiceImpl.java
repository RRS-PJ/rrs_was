package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.common.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.reservation.request.ReservationPostRequestDto;
import com.korit.projectrrs.dto.reservation.request.ReservationPutRequestDto;
import com.korit.projectrrs.dto.reservation.response.ReservationGetResponseDto;
import com.korit.projectrrs.dto.reservation.response.ReservationPostResponseDto;
import com.korit.projectrrs.dto.reservation.response.ReservationPutResponseDto;
import com.korit.projectrrs.entity.PetProfile;
import com.korit.projectrrs.entity.Provider;
import com.korit.projectrrs.entity.Reservation;
import com.korit.projectrrs.entity.User;
import com.korit.projectrrs.repositoiry.AvailableDateOfWeekRepository;
import com.korit.projectrrs.repositoiry.ProviderRepository;
import com.korit.projectrrs.repositoiry.ReservationRepository;
import com.korit.projectrrs.repositoiry.UserRepository;
import com.korit.projectrrs.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final ProviderRepository providerRepository;
    private final UserRepository userRepository;
    private final AvailableDateOfWeekRepository availableDateOfWeekRepository;
    private final PetprofileRepository petprofileRepository;

    @Override
    public ResponseDto<ReservationPostResponseDto> createReservation(String userId, ReservationPostRequestDto dto) {
        ReservationPostResponseDto data = null;
        LocalDate startDate = dto.getReservationStartDate();
        LocalDate endDate = dto.getReservationEndDate();
        String memo = dto.getReservationMemo();
        Long chosenProviderId = dto.getChosenProviderId();

        Provider chosenProvider = providerRepository.findById(chosenProviderId)
                .orElseThrow(() -> new IllegalArgumentException(ResponseMessage.NOT_EXIST_PROVIDER_ID));

        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException(ResponseMessage.NOT_EXIST_USER_ID));

        List<PetProfile> pets = petprofileRepository.findByUserId(user.getUserId());

        Reservation reservation = Reservation.builder()
            .reservationMemo(memo)
            .reservationStartDate(startDate)
            .reservationEndDate(endDate)
            .provider(chosenProvider)
            .user(user)
            .build();

        data = new ReservationPostResponseDto(reservation);

        data = data.toBuilder()
                .userPhone(user.getUserPhone())
                .pets(pets)
                .build();

        return null;
    }

    @Override
    public ResponseDto<List<ReservationGetResponseDto>> getAllReservationByUserId(String userId) {
        return null;
    }

    @Override
    public ResponseDto<ReservationGetResponseDto> getReservationByReservationId(String userId) {
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
