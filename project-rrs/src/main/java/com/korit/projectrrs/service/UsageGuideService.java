package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.useageguide.UsageGuideResponseAllDto;
import com.korit.projectrrs.dto.useageguide.UsageGuideResponseDto;

import java.util.List;

public interface UsageGuideService {
    ResponseDto<List<UsageGuideResponseAllDto>> getAllUsageGuides();
    ResponseDto<UsageGuideResponseDto> getUsageGuideById(Long guideId);
}
