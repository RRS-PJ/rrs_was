package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.customerSupport.request.CustomerSupportPostRequestDto;
import com.korit.projectrrs.dto.customerSupport.request.CustomerSupportPutRequestDto;
import com.korit.projectrrs.dto.customerSupport.response.CustomerSupportGetResponseDto;
import com.korit.projectrrs.dto.customerSupport.response.CustomerSupportPostResponseDto;
import com.korit.projectrrs.dto.customerSupport.response.CustomerSupportPutResponseDto;
import com.korit.projectrrs.security.PrincipalUser;

import java.util.List;

public interface CustomerSupportService {
    ResponseDto<CustomerSupportPostResponseDto> createCustomerSupport(Long userId, CustomerSupportPostRequestDto dto);
    ResponseDto<CustomerSupportGetResponseDto> getCustomerSupportByUserIdAndCustomerId(Long userId, Long customerSupportId);
    ResponseDto<List<CustomerSupportGetResponseDto>> getAllCustomerSupportByUserId(Long userId);
    ResponseDto<CustomerSupportPutResponseDto> updateCustomerSupport(Long userId, Long customerSupportId, CustomerSupportPutRequestDto dto);
    ResponseDto<Void> deleteCustomerService(Long customerSupportId);
}
