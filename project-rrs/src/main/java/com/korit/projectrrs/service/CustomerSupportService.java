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
    ResponseDto<CustomerSupportPostResponseDto> createCustomerSupport(PrincipalUser principalUser, CustomerSupportPostRequestDto dto);
    ResponseDto<CustomerSupportGetResponseDto> getCustomerSupportByUserIdAndCustomerId(PrincipalUser principalUser, Long customerSupportId);
    ResponseDto<List<CustomerSupportGetResponseDto>> getAllCustomerSupportByUserId(PrincipalUser principalUser);
    ResponseDto<CustomerSupportPutResponseDto> updateCustomerSupport(PrincipalUser principalUser, Long customerSupportId, CustomerSupportPutRequestDto dto);
    ResponseDto<Void> deleteCustomerService(Long customerSupportId);
}
