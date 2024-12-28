package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.common.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.useageguide.UsageGuideResponseAllDto;
import com.korit.projectrrs.dto.useageguide.UsageGuideResponseDto;
import com.korit.projectrrs.entity.UsageGuide;
import com.korit.projectrrs.repositoiry.UsageGuideRepository;
import com.korit.projectrrs.service.UsageGuideService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsageGuideServiceImpl implements UsageGuideService {

    private final UsageGuideRepository usageGuideRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<List<UsageGuideResponseAllDto>> getAllUsageGuides() {
        try {
            List<UsageGuide> usageGuides = usageGuideRepository.findAll();
            List<UsageGuideResponseAllDto> responseDtos = usageGuides.stream()
                    .map(UsageGuideResponseAllDto::new)
                    .collect(Collectors.toList());

            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDtos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<UsageGuideResponseDto> getUsageGuideById(Long guideId) {
        try {
            UsageGuide usageGuide = usageGuideRepository.findById(guideId)
                    .orElseThrow(() -> new RuntimeException(ResponseMessage.NOT_FOUND_USAGE_GUIDE));

            UsageGuideResponseDto responseDto = new UsageGuideResponseDto(usageGuide);

            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseDto.setFailed(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }
}
