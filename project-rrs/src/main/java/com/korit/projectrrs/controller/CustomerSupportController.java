package com.korit.projectrrs.controller;

import com.korit.projectrrs.common.ApiMappingPattern;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.customerSupport.request.CreateCSRequestDto;
import com.korit.projectrrs.dto.customerSupport.request.UpdateCSRequestDto;
import com.korit.projectrrs.dto.customerSupport.response.GetCSResponseDto;
import com.korit.projectrrs.dto.customerSupport.response.CreateCSResponseDto;
import com.korit.projectrrs.dto.customerSupport.response.UpdateCSResponseDto;
import com.korit.projectrrs.security.PrincipalUser;
import com.korit.projectrrs.service.CustomerSupportService;
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

    private final String CUSTOMER_SUPPORT_GET = "/{customerSupportId}";
    private final String CUSTOMER_SUPPORT_UPDATE = "/{customerSupportId}";
    private final String CUSTOMER_SUPPORT_DELETE = "/{customerSupportId}";

    @PostMapping
    private ResponseEntity<ResponseDto<CreateCSResponseDto>> createCustomerSupport (
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestBody CreateCSRequestDto dto
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
        ResponseDto<GetCSResponseDto> response = customerSupportService.getCustomerSupportByUserIdAndCustomerId(userId, customerSupportId);
        HttpStatus status = response.isResult()? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping
    private ResponseEntity<ResponseDto<List<GetCSResponseDto>>> getAllCustomerSupportByUserId (
            @AuthenticationPrincipal PrincipalUser principalUser
    ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<List<GetCSResponseDto>> response = customerSupportService.getAllCustomerSupportByUserId(userId);
        HttpStatus status = response.isResult()? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping(CUSTOMER_SUPPORT_UPDATE)
    private ResponseEntity<ResponseDto<UpdateCSResponseDto>> updateCustomerSupport (
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long customerSupportId,
            @RequestBody UpdateCSRequestDto dto
    ) {
        ResponseDto<UpdateCSResponseDto> response = customerSupportService.updateCustomerSupport(customerSupportId, dto);
        HttpStatus status = response.isResult()? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping(CUSTOMER_SUPPORT_DELETE)
    private ResponseEntity<ResponseDto<Void>> deleteCustomerSupport(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long customerSupportId
    ) {
        ResponseDto<Void> response = customerSupportService.deleteCustomerService(customerSupportId);
        HttpStatus status = response.isResult() ? HttpStatus.NO_CONTENT : HttpStatus.FORBIDDEN;
        return ResponseEntity.status(status).body(response);
    }
}