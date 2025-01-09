package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.common.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.customerSupport.request.CreateCSRequestDto;
import com.korit.projectrrs.dto.customerSupport.request.UpdateCSRequestDto;
import com.korit.projectrrs.dto.customerSupport.response.GetAllCSResponseDto;
import com.korit.projectrrs.dto.customerSupport.response.GetCSResponseDto;
import com.korit.projectrrs.dto.customerSupport.response.CreateCSResponseDto;
import com.korit.projectrrs.dto.customerSupport.response.UpdateCSResponseDto;
import com.korit.projectrrs.dto.fileUpload.response.GetFilePathAndName;
import com.korit.projectrrs.entity.CustomerSupport;
import com.korit.projectrrs.entity.CustomerSupportAttachment;
import com.korit.projectrrs.repositoiry.CustomerSupportAttachmentRepository;
import com.korit.projectrrs.repositoiry.CustomerSupportRepository;
import com.korit.projectrrs.repositoiry.UserRepository;
import com.korit.projectrrs.service.CustomerSupportService;
import com.korit.projectrrs.service.FileService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.InternalException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerSupportServiceImpl implements CustomerSupportService {
    private final CustomerSupportRepository customerSupportRepository;
    private final UserRepository userRepository;
    private final FileService fileService;
    private final CustomerSupportAttachmentRepository csAttRepository;

    @Override
    public ResponseDto<CreateCSResponseDto> createCustomerSupport(Long userId, CreateCSRequestDto dto) {
        CreateCSResponseDto data = null;
        String title = dto.getCustomerSupportTitle();
        String content = dto.getCustomerSupportContent();
        char category =  dto.getCustomerSupportCategory();

        // 유효성 검사
        if (title == null || title.isEmpty() || title.length() > 20) {
            return ResponseDto.setFailed(ResponseMessage.CS_TITLE_PROBLEM);
        }
        if (content == null || content.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.CS_CONTENT_PROBLEM);
        }
        if (category != '0' && category != '1') {
            return ResponseDto.setFailed(ResponseMessage.CS_STATUS_NOT_EXIST);
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
            List<MultipartFile> Multifiles = dto.getFiles();
            List<String > fileNames = new ArrayList<>();

            if (Multifiles == null || Multifiles.isEmpty()) {
                Multifiles = new ArrayList<>();  // 빈 배열로 초기화
            };

            for (MultipartFile multiFile : Multifiles) {
                String fileName = fileService.uploadFile(multiFile, "customer-support");
                fileNames.add(fileName);

                if (fileName != null) {
                    CustomerSupportAttachment attachment = new CustomerSupportAttachment();
                    attachment.setCustomerSupport(customerSupport);
                    attachment.setCustomerAttachmentFile(fileName);
                    csAttRepository.save(attachment);
                }
            }

            data = new CreateCSResponseDto(customerSupport).toBuilder()
                    .fileName(fileNames)
                    .build();

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

                List<CustomerSupportAttachment> csAtts = csAttRepository.findByCSId(customerSupportId);
                if (csAtts.isEmpty()){
                    csAtts = new ArrayList<>();
                }

                List<GetFilePathAndName> filesInfo = new ArrayList<>();

                for (CustomerSupportAttachment att: csAtts) {
                    File file = new File(att.getCustomerAttachmentFile());
                    filesInfo.add(new GetFilePathAndName(file));
                }

                data = new GetCSResponseDto(optionalCustomerSupport.get()).toBuilder()
                        .fileInfos(filesInfo)
                        .build();

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
    public ResponseDto<List<GetAllCSResponseDto>> getAllCSByUserId(Long userId) {
        List<GetAllCSResponseDto> data = null;
        try {
            if (!userRepository.existsById(userId)) {
                // 유저 아이디가 존재하지 않음
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);
            }
            List<CustomerSupport> customerServices = customerSupportRepository.findAllByUserId(userId);

            if(!customerServices.isEmpty()){
                data = customerServices
                        .stream().map(GetAllCSResponseDto::new)
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

        if (title == null || title.isEmpty() || title.length() > 20) {
            return ResponseDto.setFailed(ResponseMessage.CS_TITLE_PROBLEM);
        }
        if (content == null || content.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.CS_CONTENT_PROBLEM);
        }

        try {
            CustomerSupport cs = customerSupportRepository.findById(customerSupportId)
                    .orElseThrow(() ->  new InternalException(ResponseMessage.NOT_MATCH_USER_ID));
            cs = cs.toBuilder()
                    .customerSupportTitle(title)
                        .customerSupportContent(content)
                        .customerSupportCreateAt(LocalDateTime.now())
                        .build();

            customerSupportRepository.save(cs);

            List<CustomerSupportAttachment> currentAtt = csAttRepository.findByCSId(cs.getCsId());
            for (CustomerSupportAttachment attachment : currentAtt) {
                fileService.removeFile(attachment.getCustomerAttachmentFile());
                csAttRepository.delete(attachment);
            }

            List<MultipartFile> newFiles = dto.getFiles();
            if (newFiles != null && !newFiles.isEmpty()) {
                for (MultipartFile file : newFiles) {
                    String filePath = fileService.uploadFile(file, dto.getPath());
                    if (filePath != null) {
                        CustomerSupportAttachment attachment = CustomerSupportAttachment.builder()
                                .customerSupport(cs)
                                .customerAttachmentFile(filePath)
                                .build();
                        csAttRepository.save(attachment);
                    }
                }
            }

            List<String> filePaths = csAttRepository.findByCSId(cs.getCsId())
                    .stream()
                    .map(CustomerSupportAttachment::getCustomerAttachmentFile)
                    .toList();

            data = new UpdateCSResponseDto(cs, filePaths);

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

            List<CustomerSupportAttachment> currentAtt = csAttRepository.findByCSId(customerSupportId);
            for (CustomerSupportAttachment attachment : currentAtt) {
                fileService.removeFile(attachment.getCustomerAttachmentFile());
                csAttRepository.delete(attachment);
            }

            customerSupportRepository.deleteById(customerSupportId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }
}