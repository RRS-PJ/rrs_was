package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.common.constant.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.availableDateOfWeek.responseDto.AvailableDateOfWeekResponseDto;
import com.korit.projectrrs.dto.provider.request.ProviderRequestDto;
import com.korit.projectrrs.dto.provider.response.GetOneProviderInfoResponseDto;
import com.korit.projectrrs.dto.provider.response.ProviderResponseDto;
import com.korit.projectrrs.entity.AvailableDateOfWeek;
import com.korit.projectrrs.entity.User;
import com.korit.projectrrs.repositoiry.ReviewRepository;
import com.korit.projectrrs.repositoiry.UserRepository;
import com.korit.projectrrs.service.AvailableDateOfWeekService;
import com.korit.projectrrs.service.ProviderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.InternalException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProviderServiceImpl implements ProviderService {

    private final AvailableDateOfWeekService availableDateOfWeekService;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public ResponseDto<ProviderResponseDto> updateProvider(Long userId, ProviderRequestDto dto) {
        List<LocalDate> availableDates = dto.getAvailableDate();
        String providerIntroduction = dto.getProviderIntroduction();

        ProviderResponseDto data = null;

        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException(ResponseMessage.NOT_EXIST_USER_ID));

            boolean hasProviderRole = Arrays.stream(user.getRoles().split(", "))
                    .anyMatch(role -> role.trim().equals("ROLE_PROVIDER"));

            if (hasProviderRole) {
                List<AvailableDateOfWeek> createdDates = new ArrayList<>();
                for (LocalDate date : availableDates) {
                    AvailableDateOfWeek createDate = availableDateOfWeekService.addDate(userId, date);
                    createdDates.add(createDate);
                }

                List<AvailableDateOfWeekResponseDto> responseList = createdDates.stream()
                        .map(AvailableDateOfWeekResponseDto::new)
                        .collect(Collectors.toList());

                user = user.toBuilder()
                        .providerIntroduction(providerIntroduction)
                        .build();

                userRepository.save(user);

                data = new ProviderResponseDto(responseList, providerIntroduction);
            } else {
                data = new ProviderResponseDto(new ArrayList<>(), null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<ProviderResponseDto> getProviderInfo(Long userId) {
        return null;
    }

    @Override
    public ResponseDto<GetOneProviderInfoResponseDto> getOneProviderInfoById(Long providerId) {
        GetOneProviderInfoResponseDto data = null;
        try {
            User provider = userRepository.findProviderById(providerId)
                    .orElseThrow(() -> new InternalException(ResponseMessage.NOT_EXIST_PROVIDER_ID));
            Double avgReview = reviewRepository.findAvgReviewScoreByProvider(providerId)
                    .orElse(0.0);

            data = new GetOneProviderInfoResponseDto(provider, avgReview);
        }catch (Exception e) {
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

//    @Override
//    public ResponseDto<ProviderResponseDto> getProviderInfo(Long userId) {
//        ProviderResponseDto data = null;
//        List<AvailableDateOfWeek> availableDates = null;
//        String providerIntroduction = null;
//
//        try {
//            Optional<User> optionalUser = userRepository.findById(userId);
//
//            if (optionalUser.isEmpty()) {
//                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);
//            }
//
//            User user = optionalUser.get();
//
//            boolean isActive = user.getRoles().contains("ROLE_PROVIDER");
//
//            if (isActive) {
//                availableDates = user.getAvailableDateOfWeek();
//                providerIntroduction = user.getProviderIntroduction();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
//        }
//
//        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
//    }
}
