package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.common.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.customerSupport.request.CustomerSupportPostRequestDto;
import com.korit.projectrrs.dto.customerSupport.request.CustomerSupportPutRequestDto;
import com.korit.projectrrs.dto.customerSupport.response.CustomerSupportGetResponseDto;
import com.korit.projectrrs.dto.customerSupport.response.CustomerSupportPostResponseDto;
import com.korit.projectrrs.dto.customerSupport.response.CustomerSupportPutResponseDto;
import com.korit.projectrrs.entity.CustomerSupport;
import com.korit.projectrrs.repositoiry.CustomerSupportRepository;
import com.korit.projectrrs.repositoiry.UserRepository;
import com.korit.projectrrs.service.CustomerSupportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerSupportServiceImpl implements CustomerSupportService {
    private final CustomerSupportRepository customerSupportRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseDto<CustomerSupportPostResponseDto> createCustomerSupport(String userId, CustomerSupportPostRequestDto dto) {
        CustomerSupportPostResponseDto data = null;
        String title = dto.getCustomerSupportTitle();
        String content = dto.getCustomerSupportContent();
        char category =  dto.getCustomerSupportCategory();

        // 요효성 검사
        if (title == null || title.isEmpty() || title.length() > 20) {
            return ResponseDto.setFailed(ResponseMessage.BAD_REQUEST);
        }
        if (content == null || content.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.BAD_REQUEST);
        }
        if (category != '0' && category != '1') {
            return ResponseDto.setFailed(ResponseMessage.BAD_REQUEST);
        }

        try {
            CustomerSupport customerSupport =  CustomerSupport.builder()
                    .user(userRepository.findByUserId(userId).get())
                    .customerSupportTitle(title)
                    .customerSupportContent(content)
                    .customerSupportCategory(category)
                    .customerSupportStatus('0')
                    .customerSupportCreateAt(LocalDateTime.now())
                    .build();
            customerSupportRepository.save(customerSupport);
            data = new CustomerSupportPostResponseDto(customerSupport);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<CustomerSupportGetResponseDto> getCustomerSupportByUserIdAndCustomerId(String userId, Long customerSupportId) {
        CustomerSupportGetResponseDto data = null;
        try {
            if (!userRepository.existsByUserId(userId)) {
                // 유저 아이디가 존재하지 않음
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);
            }

            Optional<CustomerSupport> optionalCustomerSupport = customerSupportRepository.findById(customerSupportId);

            if (optionalCustomerSupport.isPresent()) {
                // 유저 아이디 불일치 시
                if (!userId.equals(optionalCustomerSupport.get().getUser().getUserId())) {
                    return ResponseDto.setFailed(ResponseMessage.NOT_MATCH_USER_ID);
                }
                data = new CustomerSupportGetResponseDto(optionalCustomerSupport.get());
            } else {
                // 고객센터 포스트가 존재하지 않음
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_CUSTOMER_SUPPORT);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<List<CustomerSupportGetResponseDto>> getAllCustomerSupportByUserId(String userId) {
        List<CustomerSupportGetResponseDto> data = null;
        try {
            if (!userRepository.existsByUserId(userId)) {
                // 유저 아이디가 존재하지 않음
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);
            }
            Optional<List<CustomerSupport>> optionalCustomerServices = customerSupportRepository.findAllByUserId(userId);

            if(optionalCustomerServices.isPresent()){
                data = optionalCustomerServices.get()
                        .stream().map(CustomerSupportGetResponseDto::new)
                        .collect(Collectors.toList());
            } else {
                // 고객센터 포스트가 존재하지 않음
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_CUSTOMER_SUPPORT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<CustomerSupportPutResponseDto> updateCustomerSupport(String userId, Long customerSupportId, CustomerSupportPutRequestDto dto) {
        CustomerSupportPutResponseDto data = null;
        String title = dto.getCustomerSupportTitle();
        String content = dto.getCustomerSupportContent();

        // 요효성 검사
        if (title == null || title.isEmpty() || title.length() > 20) {
            return ResponseDto.setFailed(ResponseMessage.BAD_REQUEST);
        }
        if (content == null || content.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.BAD_REQUEST);
        }

        try {
            Optional<CustomerSupport> optionalCustomerSupport = customerSupportRepository.findByUserIdAndCustomerSupportId(userId, customerSupportId);
            if (optionalCustomerSupport.isPresent()) {

                CustomerSupport responsedCustomerSupport = optionalCustomerSupport.get();
                responsedCustomerSupport.setCustomerSupportTitle(title);
                responsedCustomerSupport.setCustomerSupportContent(content);

                data = new CustomerSupportPutResponseDto(responsedCustomerSupport);

            } else {
                // 고객센터 포스트가 존재하지 않음
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_CUSTOMER_SUPPORT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<Void> deleteCustomerService(Long customerSupportId) {
        try {
            if(!customerSupportRepository.existsById(customerSupportId)) {
                ResponseDto.setFailed(ResponseMessage.NOT_EXIST_CUSTOMER_SUPPORT);
            }
            customerSupportRepository.deleteById(customerSupportId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }
}