package com.korit.projectrrs.controller;

import com.korit.projectrrs.common.ApiMappingPattern;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiMappingPattern.CUSTOMER_SUPPORT)
@RequiredArgsConstructor
public class CustomerSupportController {
//    private final CustomerSupportService customerSupportService;
//
//    private final String CUSTOMER_SUPPORT_GET = "/{”customer_support_Id”}";
//    private final String CUSTOMER_SUPPORT_UPDATE = "/{”customer_support_Id”}";
//    private final String CUSTOMER_SUPPORT_DELETE = "/{”customer_support_Id”}";
//
//    @PostMapping
//    private ResponseEntity<ResponseDto<CustomerSupportCreateResponseDto>> createCustomerSupport (
//            @AuthenticationPrincipal String userId,
//            @RequestBody CustomerSupportCreateRequestDto dto
//            ) {
//        ResponseDto<CustomerSupportCreateResponseDto> response = customerSupportService.createCustomerSupport(dto);
//        HttpStatus status = response.isResult()? HttpStatus.OK : HttpStatus.BAD_REQUEST;
//        return ResponseEntity.status(status).body(response);
//    }
//
//    @GetMapping
//    private ResponseEntity<ResponseDto<CustomerSupportGetResponseDto>> getCustomerSupportByUserId (
//            @AuthenticationPrincipal String userId,
//            @PathVariable Long customerSupportId
//    ) {
//        ResponseDto<CustomerSupportGetResponseDto> response = customerSupportService.getCustomerSupportByUserId(userId, customerSupportId);
//        HttpStatus status = response.isResult()? HttpStatus.OK : HttpStatus.BAD_REQUEST;
//        return ResponseEntity.status(status).body(response);
//    }
//
//    @GetMapping
//    private ResponseEntity<ResponseDto<List<CustomerSupportGetResponseDto>>> getAllCustomerSupportByUserId (
//            @AuthenticationPrincipal String userId,
//            @PathVariable Long customerSupportId
//    ) {
//        ResponseDto<List<CustomerSupportGetResponseDto>> response = customerSupportService.getAllCustomerSupportByUserId(userId, customerSupportId);
//        HttpStatus status = response.isResult()? HttpStatus.OK : HttpStatus.BAD_REQUEST;
//        return ResponseEntity.status(status).body(response);
//    }
//
//    @PutMapping
//    private ResponseEntity<ResponseDto<CustomerSupportUpdateResponseDto>> createCustomerSupport (
//            @AuthenticationPrincipal String userId,
//            @PathVariable Long customerSupportId,
//            @RequestBody CustomerSupportUpdateRequestDto dto
//    ) {
//        ResponseDto<CustomerSupportUpdateResponseDto> response = customerSupportService.updateCustomerSupport(userId, customerSupportId, dto);
//        HttpStatus status = response.isResult()? HttpStatus.OK : HttpStatus.BAD_REQUEST;
//        return ResponseEntity.status(status).body(response);
//    }
//
//    @DeleteMapping
//    private ResponseEntity<ResponseDto<Void>> deleteCustomerSupport(
//            @AuthenticationPrincipal String userId,
//            @PathVariable Long customerSupportId
//    ) {
//        ResponseDto<Void> response = customerSupportService.deleteCustomerService(customerSupportId);
//        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
//        return ResponseEntity.status(status).body(response);
//    }
}
