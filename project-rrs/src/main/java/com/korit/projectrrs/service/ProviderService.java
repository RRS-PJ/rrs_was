package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.provider.request.ProviderRequestDto;
import com.korit.projectrrs.dto.provider.response.GetOneProviderInfoResponseDto;
import com.korit.projectrrs.dto.provider.response.ProviderResponseDto;

public interface ProviderService {
    ResponseDto<ProviderResponseDto> updateProvider(Long userId, ProviderRequestDto dto);
    ResponseDto<ProviderResponseDto> getProviderInfo(Long userId);
    ResponseDto<GetOneProviderInfoResponseDto> getOneProviderInfoById(Long providerId);
}
