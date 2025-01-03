package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.common.ResponseMessage;
import com.korit.projectrrs.entity.AvailableDateOfWeek;
import com.korit.projectrrs.entity.User;
import com.korit.projectrrs.repositoiry.AvailableDateOfWeekRepository;
import com.korit.projectrrs.repositoiry.UserRepository;
import com.korit.projectrrs.service.AvailableDateOfWeekService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AvailableDateOfWeekServiceImpl implements AvailableDateOfWeekService {
    private final AvailableDateOfWeekRepository availableDateOfWeekRepository;
    private final UserRepository userRepository;


    @Override
    public AvailableDateOfWeek addDate(Long userId, LocalDate availableDate) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException(ResponseMessage.NOT_EXIST_USER_ID));

        AvailableDateOfWeek availableDateOfWeek = AvailableDateOfWeek.builder()
                .provider(user)
                .availableDate(availableDate)
                .build();

        return availableDateOfWeekRepository.save(availableDateOfWeek);
    }

    @Override
    public void reomoveDate(Long userId, Long availableDateOfWeekId) {
        Optional<AvailableDateOfWeek> optionalAvailableDateOfWeek = availableDateOfWeekRepository.findByUserIdAndAvailableDateAndId(userId, availableDateOfWeekId);

        if (optionalAvailableDateOfWeek.isEmpty()) {
            throw new RuntimeException(ResponseMessage.NOT_EXIST_AVAILABLEDATE_ID);
        }

        AvailableDateOfWeek availableDateOfWeek = optionalAvailableDateOfWeek.get();
        availableDateOfWeekRepository.delete(availableDateOfWeek);
    }
}
