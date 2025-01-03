package com.korit.projectrrs.controller;

import com.korit.projectrrs.common.ApiMappingPattern;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.useageguide.UsageGuideResponseAllDto;
import com.korit.projectrrs.dto.useageguide.UsageGuideResponseDto;
import com.korit.projectrrs.service.UsageGuideService;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.korit.projectrrs.common.ApiMappingPattern.USAGE_GUIDE_BY_ID;

@RestController
@RequestMapping(ApiMappingPattern.USAGE_GUIDE)
@RequiredArgsConstructor
public class UsageGuideController {

    private final UsageGuideService usageGuideService;

    @GetMapping
    @PermitAll
    public ResponseEntity<ResponseDto<List<UsageGuideResponseAllDto>>> getAllUsageGuides() {
        ResponseDto<List<UsageGuideResponseAllDto>> response = usageGuideService.getAllUsageGuides();
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NO_CONTENT;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(USAGE_GUIDE_BY_ID)
    @PermitAll
    public ResponseEntity<ResponseDto<UsageGuideResponseDto>> getUsageGuideById(@PathVariable("usageGuideId") Long guideId) {
        ResponseDto<UsageGuideResponseDto> response = usageGuideService.getUsageGuideById(guideId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }
}
