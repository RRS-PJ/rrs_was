package com.korit.projectrrs.controller;

import com.korit.projectrrs.common.ApiMappingPattern;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.customerSupport.request.CustomerSupportPostRequestDto;
import com.korit.projectrrs.dto.customerSupport.request.CustomerSupportPutRequestDto;
import com.korit.projectrrs.dto.customerSupport.response.CustomerSupportGetResponseDto;
import com.korit.projectrrs.dto.customerSupport.response.CustomerSupportPostResponseDto;
import com.korit.projectrrs.dto.customerSupport.response.CustomerSupportPutResponseDto;
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
    private ResponseEntity<ResponseDto<CustomerSupportPostResponseDto>> createCustomerSupport (
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestBody CustomerSupportPostRequestDto dto
            ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<CustomerSupportPostResponseDto> response = customerSupportService.createCustomerSupport(userId, dto);
        HttpStatus status = response.isResult()? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(CUSTOMER_SUPPORT_GET)
    private ResponseEntity<ResponseDto<CustomerSupportGetResponseDto>> getCustomerSupportByUserIdAndCustomerId (
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long customerSupportId
    ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<CustomerSupportGetResponseDto> response = customerSupportService.getCustomerSupportByUserIdAndCustomerId(userId, customerSupportId);
        HttpStatus status = response.isResult()? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping
    private ResponseEntity<ResponseDto<List<CustomerSupportGetResponseDto>>> getAllCustomerSupportByUserId (
            @AuthenticationPrincipal PrincipalUser principalUser
    ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<List<CustomerSupportGetResponseDto>> response = customerSupportService.getAllCustomerSupportByUserId(userId);
        HttpStatus status = response.isResult()? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping(CUSTOMER_SUPPORT_UPDATE)
    private ResponseEntity<ResponseDto<CustomerSupportPutResponseDto>> updateCustomerSupport (
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long customerSupportId,
            @RequestBody CustomerSupportPutRequestDto dto
    ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<CustomerSupportPutResponseDto> response = customerSupportService.updateCustomerSupport(userId, customerSupportId, dto);
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