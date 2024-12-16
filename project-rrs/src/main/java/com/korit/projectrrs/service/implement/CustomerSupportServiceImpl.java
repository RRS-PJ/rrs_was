package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.common.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.customerSupport.request.CreateCSRequestDto;
import com.korit.projectrrs.dto.customerSupport.request.UpdateCSRequestDto;
import com.korit.projectrrs.dto.customerSupport.response.GetCSResponseDto;
import com.korit.projectrrs.dto.customerSupport.response.CreateCSResponseDto;
import com.korit.projectrrs.dto.customerSupport.response.UpdateCSResponseDto;
import com.korit.projectrrs.dto.fileUpload.request.UploadFileRequestDto;
import com.korit.projectrrs.entity.CustomerSupport;
import com.korit.projectrrs.entity.CustomerSupportAttachment;
import com.korit.projectrrs.repositoiry.CustomerSupportAttachmentRepository;
import com.korit.projectrrs.repositoiry.CustomerSupportRepository;
import com.korit.projectrrs.repositoiry.UserRepository;
import com.korit.projectrrs.service.CustomerSupportService;
import com.korit.projectrrs.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.InternalException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerSupportServiceImpl implements CustomerSupportService {
    private final CustomerSupportRepository customerSupportRepository;
    private final UserRepository userRepository;
    private final FileUploadService fileUploadService;
    private final CustomerSupportAttachmentRepository customerSupportAttachmentRepository;

    @Override
    public ResponseDto<CreateCSResponseDto> createCustomerSupport(Long userId, CreateCSRequestDto dto, UploadFileRequestDto fileDto) {
        CreateCSResponseDto data = null;
        String title = dto.getCustomerSupportTitle();
        String content = dto.getCustomerSupportContent();
        char category =  dto.getCustomerSupportCategory();

        // 유효성 검사
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
                    .user(userRepository.findById(userId)
                            .orElseThrow(() -> new InternalException(ResponseMessage.NOT_EXIST_USER_ID)))
                    .customerSupportTitle(title)
                    .customerSupportContent(content)
                    .customerSupportCategory(category)
                    .customerSupportStatus('0')
                    .customerSupportCreateAt(LocalDateTime.now())
                    .build();

            customerSupportRepository.save(customerSupport);

            // 파일 업로드 처리
            List<MultipartFile> files = fileDto.getFile();
            for (MultipartFile file : files) {
                String fileUrl = fileUploadService.UploadFile(file);

                if (fileUrl != null) {
                    CustomerSupportAttachment attachment = new CustomerSupportAttachment();
                    attachment.setCustomerSupport(customerSupport);
                    attachment.setCustomerAttachmentFile(fileUrl);
                    customerSupportAttachmentRepository.save(attachment);
                }
            }

            data = new CreateCSResponseDto(customerSupport);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<GetCSResponseDto> getCSByUserIdAndCustomerId(Long userId, Long customerSupportId) {
        GetCSResponseDto data = null;
        try {
            if (!userRepository.existsById(userId)) {
                // 유저 아이디가 존재하지 않음
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);
            }

            Optional<CustomerSupport> optionalCustomerSupport = customerSupportRepository.findById(customerSupportId);

            if (optionalCustomerSupport.isPresent()) {
                // 유저 아이디 불일치 시
                if (!userId.equals(optionalCustomerSupport.get().getUser().getUserId())) {
                    return ResponseDto.setFailed(ResponseMessage.NOT_MATCH_USER_ID);
                }
                data = new GetCSResponseDto(optionalCustomerSupport.get());
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
    public ResponseDto<List<GetCSResponseDto>> getAllCSByUserId(Long userId) {
        List<GetCSResponseDto> data = null;
        try {
            if (!userRepository.existsById(userId)) {
                // 유저 아이디가 존재하지 않음
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);
            }
            List<CustomerSupport> customerServices = customerSupportRepository.findAllByUserId(userId);

            if(!customerServices.isEmpty()){
                data = customerServices
                        .stream().map(GetCSResponseDto::new)
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
    public ResponseDto<UpdateCSResponseDto> updateCS(Long userId, Long customerSupportId, UpdateCSRequestDto dto) {
        UpdateCSResponseDto data = null;
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
            Optional<CustomerSupport> optionalCustomerSupport = customerSupportRepository.findById(customerSupportId);
            if (optionalCustomerSupport.isPresent()) {

                CustomerSupport responsedCustomerSupport = optionalCustomerSupport.get();

                if (!responsedCustomerSupport.getUser().getUserId().equals(userId)) {
                    return ResponseDto.setFailed(ResponseMessage.NOT_MATCH_USER_ID);
                }
                responsedCustomerSupport.setCustomerSupportTitle(title);
                responsedCustomerSupport.setCustomerSupportContent(content);

                data = new UpdateCSResponseDto(responsedCustomerSupport);

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
    public ResponseDto<Void> deleteCS(Long userId, Long customerSupportId) {
        try {
            CustomerSupport customerSupport = customerSupportRepository.findById(customerSupportId)
                    .orElseThrow(() -> new InternalException(ResponseMessage.NOT_EXIST_CUSTOMER_SUPPORT));

            if (!customerSupport.getUser().getUserId().equals(userId)){
                return ResponseDto.setFailed(ResponseMessage.NOT_MATCH_USER_ID);
            }

            customerSupportRepository.deleteById(customerSupportId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }
}