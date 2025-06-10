package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.provision.responseDto.ProvisionListResponseDto;
import com.korit.projectrrs.dto.provision.responseDto.ProvisionResponseDto;
import java.util.List;

public interface ProvisionService {
    ResponseDto<List<ProvisionListResponseDto>> getProvisionList(Long providerId);
    ResponseDto<ProvisionResponseDto> getProvisionInfo(Long providerId, Long reservationId);
}
