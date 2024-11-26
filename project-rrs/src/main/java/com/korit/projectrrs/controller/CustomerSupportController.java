package com.korit.projectrrs.controller;

import com.korit.projectrrs.common.ApiMappingPattern;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.customerSupport.request.CustomerSupportPostRequestDto;
import com.korit.projectrrs.dto.customerSupport.request.CustomerSupportPutRequestDto;
import com.korit.projectrrs.dto.customerSupport.response.CustomerSupportGetResponseDto;
import com.korit.projectrrs.dto.customerSupport.response.CustomerSupportPostResponseDto;
import com.korit.projectrrs.dto.customerSupport.response.CustomerSupportPutResponseDto;
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
            @AuthenticationPrincipal String userId,
            @RequestBody CustomerSupportPostRequestDto dto
            ) {
        ResponseDto<CustomerSupportPostResponseDto> response = customerSupportService.createCustomerSupport(userId, dto);
        HttpStatus status = response.isResult()? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(CUSTOMER_SUPPORT_GET)
    private ResponseEntity<ResponseDto<CustomerSupportGetResponseDto>> getCustomerSupportByUserIdAndCustomerId (
            @AuthenticationPrincipal String userId,
            @PathVariable Long customerSupportId
    ) {
        ResponseDto<CustomerSupportGetResponseDto> response = customerSupportService.getCustomerSupportByUserId(userId, customerSupportId);
        HttpStatus status = response.isResult()? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping
    private ResponseEntity<ResponseDto<List<CustomerSupportGetResponseDto>>> getAllCustomerSupportByUserId (
            @AuthenticationPrincipal String userId
    ) {
        ResponseDto<List<CustomerSupportGetResponseDto>> response = customerSupportService.getAllCustomerSupportByUserId(userId);
        HttpStatus status = response.isResult()? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping(CUSTOMER_SUPPORT_UPDATE)
    private ResponseEntity<ResponseDto<CustomerSupportPutResponseDto>> updateCustomerSupport (
            @AuthenticationPrincipal String userId,
            @PathVariable Long customerSupportId,
            @RequestBody CustomerSupportPutRequestDto dto
    ) {
        ResponseDto<CustomerSupportPutResponseDto> response = customerSupportService.updateCustomerSupport(userId, customerSupportId, dto);
        HttpStatus status = response.isResult()? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping(CUSTOMER_SUPPORT_DELETE)
    private ResponseEntity<ResponseDto<Void>> deleteCustomerSupport(
            @AuthenticationPrincipal String userId,
            @PathVariable Long customerSupportId
    ) {
        ResponseDto<Void> response = customerSupportService.deleteCustomerService(customerSupportId);
        HttpStatus status = response.isResult() ? HttpStatus.NO_CONTENT : HttpStatus.FORBIDDEN;
        return ResponseEntity.status(status).body(response);
    }
}