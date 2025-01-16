package com.korit.projectrrs.controller;

import com.korit.projectrrs.common.constant.ApiMappingPattern;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.customerSupport.request.CreateCSRequestDto;
import com.korit.projectrrs.dto.customerSupport.request.UpdateCSRequestDto;
import com.korit.projectrrs.dto.customerSupport.response.GetAllCSResponseDto;
import com.korit.projectrrs.dto.customerSupport.response.GetCSResponseDto;
import com.korit.projectrrs.dto.customerSupport.response.CreateCSResponseDto;
import com.korit.projectrrs.dto.customerSupport.response.UpdateCSResponseDto;
import com.korit.projectrrs.security.PrincipalUser;
import com.korit.projectrrs.service.CustomerSupportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.CUSTOMER_SUPPORT)
@RequiredArgsConstructor
public class CustomerSupportController {
    private final CustomerSupportService customerSupportService;

    private final String CUSTOMER_SUPPORT_CREATE = "/write";
    private final String CUSTOMER_SUPPORT_GET = "/{customerSupportId}";
    private final String CUSTOMER_SUPPORT_UPDATE = "/{customerSupportId}";
    private final String CUSTOMER_SUPPORT_DELETE = "/{customerSupportId}";

    @PostMapping(CUSTOMER_SUPPORT_CREATE)
    private ResponseEntity<ResponseDto<CreateCSResponseDto>> createCustomerSupport (
            @AuthenticationPrincipal PrincipalUser principalUser,
            @Valid @ModelAttribute CreateCSRequestDto dto
            ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<CreateCSResponseDto> response = customerSupportService.createCustomerSupport(userId, dto);
        HttpStatus status = response.isResult()? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(CUSTOMER_SUPPORT_GET)
    private ResponseEntity<ResponseDto<GetCSResponseDto>> getCustomerSupportByUserIdAndCustomerId (
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long customerSupportId
    ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<GetCSResponseDto> response = customerSupportService.getCSByUserIdAndCustomerId(userId, customerSupportId);
        HttpStatus status = response.isResult()? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping
    private ResponseEntity<ResponseDto<List<GetAllCSResponseDto>>> getAllCustomerSupportByUserId (
            @AuthenticationPrincipal PrincipalUser principalUser
    ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<List<GetAllCSResponseDto>> response = customerSupportService.getAllCSByUserId(userId);
        HttpStatus status = response.isResult()? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping(CUSTOMER_SUPPORT_UPDATE)
    private ResponseEntity<ResponseDto<UpdateCSResponseDto>> updateCustomerSupport (
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long customerSupportId,
            @Valid @ModelAttribute UpdateCSRequestDto dto
    ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<UpdateCSResponseDto> response = customerSupportService.updateCS(userId, customerSupportId, dto);
        HttpStatus status = response.isResult()? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping(CUSTOMER_SUPPORT_DELETE)
    private ResponseEntity<ResponseDto<Void>> deleteCustomerSupport(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long customerSupportId
    ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<Void> response = customerSupportService.deleteCS(userId, customerSupportId);
        HttpStatus status = response.isResult() ? HttpStatus.NO_CONTENT : HttpStatus.FORBIDDEN;
        return ResponseEntity.status(status).body(response);
    }
}