package com.korit.projectrrs.controller;

import com.korit.projectrrs.common.constant.ApiMappingPattern;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.provision.responseDto.ProvisionListResponseDto;
import com.korit.projectrrs.dto.provision.responseDto.ProvisionResponseDto;
import com.korit.projectrrs.security.PrincipalUser;
import com.korit.projectrrs.service.ProvisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.PROVISION)
@RequiredArgsConstructor
public class ProvisionController {
    private final ProvisionService provisionService;

    private static final String PET_GET_BY_ID = "/{reservationId}";

    @GetMapping
    public ResponseEntity<ResponseDto<List<ProvisionListResponseDto>>> getProvisionList(
            @AuthenticationPrincipal PrincipalUser principalUser
    ) {
        Long providerId = principalUser.getUser().getUserId();
        ResponseDto<List<ProvisionListResponseDto>> response = provisionService.getProvisionList(providerId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(PET_GET_BY_ID)
    public ResponseEntity<ResponseDto<ProvisionResponseDto>> getProvisionInfo(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long reservationId
    ) {
        Long providerId = principalUser.getUser().getUserId();
        ResponseDto<ProvisionResponseDto> response = provisionService.getProvisionInfo(providerId, reservationId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }
}