package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.common.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.customerSupportController.request.CustomerSupportPostRequestDto;
import com.korit.projectrrs.dto.customerSupportController.request.CustomerSupportUpdateRequestDto;
import com.korit.projectrrs.dto.customerSupportController.response.CustomerSupportGetResponseDto;
import com.korit.projectrrs.dto.customerSupportController.response.CustomerSupportPostResponseDto;
import com.korit.projectrrs.dto.customerSupportController.response.CustomerSupportUpdateResponseDto;
import com.korit.projectrrs.entity.CustomerSupport;
import com.korit.projectrrs.repositoiry.CustomerSupportRepository;
import com.korit.projectrrs.repositoiry.UserRepository;
import com.korit.projectrrs.service.CustomerSupportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerSupportServiceImpl implements CustomerSupportService {
    private final CustomerSupportRepository customerSupportRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseDto<CustomerSupportPostResponseDto> createCustomerSupport(CustomerSupportPostRequestDto dto) {
        CustomerSupportPostResponseDto data = null;
        String title = dto.getCustomerSupportTitle();
        String content = dto.getCustomerSupportContent();
        char category =  dto.getCustomerSupportCategory();

        try {
            CustomerSupport customerSupport =  CustomerSupport.builder()
                    .customerSupportTitle(title)
                    .customerSupportContent(content)
                    .customerSupportCategory(category)
                    .customerSupportStatus('0')
                    .customerSupportCreateAt(LocalDate.now())
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<CustomerSupportGetResponseDto> getCustomerSupportByUserId(String userId, Long customerSupportId) {
        CustomerSupportGetResponseDto data = null;
        try {

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<List<CustomerSupportGetResponseDto>> getAllCustomerSupportByUserId(String userId, Long customerSupportId) {
        List<CustomerSupportGetResponseDto> data = null;
        try {

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<CustomerSupportUpdateResponseDto> updateCustomerSupport(String userId, Long customerSupportId, CustomerSupportUpdateRequestDto dto) {
        CustomerSupportUpdateResponseDto data = null;
        try {

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<Void> deleteCustomerService(Long customerSupportId) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }
}
