package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.common.constant.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.provision.responseDto.ProvisionListResponseDto;
import com.korit.projectrrs.dto.provision.responseDto.ProvisionResponseDto;
import com.korit.projectrrs.entity.Reservation;
import com.korit.projectrrs.repositoiry.ReservationRepository;
import com.korit.projectrrs.service.ProvisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProvisionServiceImpl implements ProvisionService {
    private final ReservationRepository reservationRepository;

    @Override
    public ResponseDto<List<ProvisionListResponseDto>> getProvisionList(Long providerId) {
        List<ProvisionListResponseDto> data = new ArrayList<>();

        try {
            List<Reservation> provision = reservationRepository.findAllByProviderId(providerId);

            data = provision.stream()
                    .map(ProvisionListResponseDto::new)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<ProvisionResponseDto> getProvisionInfo(Long providerId, Long reservationId) {
        ProvisionResponseDto data = null;

        try {
            Optional<Reservation> optionalProvision = reservationRepository.findById(reservationId);

            if (optionalProvision.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_PROVISION);
            }

            Reservation provision = optionalProvision.get();

            data = new ProvisionResponseDto(provision);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}