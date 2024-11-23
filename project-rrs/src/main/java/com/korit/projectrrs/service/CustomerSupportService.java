package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.customerSupportController.request.CustomerSupportPostRequestDto;
import com.korit.projectrrs.dto.customerSupportController.request.CustomerSupportUpdateRequestDto;
import com.korit.projectrrs.dto.customerSupportController.response.CustomerSupportGetResponseDto;
import com.korit.projectrrs.dto.customerSupportController.response.CustomerSupportPostResponseDto;
import com.korit.projectrrs.dto.customerSupportController.response.CustomerSupportUpdateResponseDto;

import java.util.List;

public interface CustomerSupportService {
    ResponseDto<CustomerSupportPostResponseDto> createCustomerSupport(String userId, CustomerSupportPostRequestDto dto);

    ResponseDto<CustomerSupportGetResponseDto> getCustomerSupportByUserId(String userId, Long customerSupportId);

    ResponseDto<List<CustomerSupportGetResponseDto>> getAllCustomerSupportByUserId(String userId);

    ResponseDto<CustomerSupportUpdateResponseDto> updateCustomerSupport(String userId, Long customerSupportId, CustomerSupportUpdateRequestDto dto);

    ResponseDto<Void> deleteCustomerService(Long customerSupportId);
}
