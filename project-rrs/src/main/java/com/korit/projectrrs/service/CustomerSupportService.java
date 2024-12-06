package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.customerSupport.request.CustomerSupportPostRequestDto;
import com.korit.projectrrs.dto.customerSupport.request.CustomerSupportPutRequestDto;
import com.korit.projectrrs.dto.customerSupport.response.CustomerSupportGetResponseDto;
import com.korit.projectrrs.dto.customerSupport.response.CustomerSupportPostResponseDto;
import com.korit.projectrrs.dto.customerSupport.response.CustomerSupportPutResponseDto;

import java.util.List;

public interface CustomerSupportService {
    ResponseDto<CustomerSupportPostResponseDto> createCustomerSupport(String userId, CustomerSupportPostRequestDto dto);
    ResponseDto<CustomerSupportGetResponseDto> getCustomerSupportByUserIdAndCustomerId(String userId, Long customerSupportId);
    ResponseDto<List<CustomerSupportGetResponseDto>> getAllCustomerSupportByUserId(String userId);
    ResponseDto<CustomerSupportPutResponseDto> updateCustomerSupport(String userId, Long customerSupportId, CustomerSupportPutRequestDto dto);
    ResponseDto<Void> deleteCustomerService(Long customerSupportId);
}
