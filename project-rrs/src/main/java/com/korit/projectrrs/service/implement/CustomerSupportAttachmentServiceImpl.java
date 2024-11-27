package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.common.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.customerSupportAttachment.response.CustomerSupportAttachmentResponseDto;
import com.korit.projectrrs.entity.CustomerSupport;
import com.korit.projectrrs.entity.CustomerSupportAttachment;
import com.korit.projectrrs.repositoiry.CustomerSupportAttachmentRepository;
import com.korit.projectrrs.repositoiry.CustomerSupportRepository;
import com.korit.projectrrs.service.CustomerSupportAttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerSupportAttachmentServiceImpl implements CustomerSupportAttachmentService {

    private final CustomerSupportRepository customerSupportRepository;
    private final CustomerSupportAttachmentRepository customerSupportAttachmentRepository;

    @Override
    public ResponseDto<List<CustomerSupportAttachment>> saveAttachments(List<MultipartFile> files, Long customerSupportId) {
        List<CustomerSupportAttachment> attachments = new ArrayList<>();

        // 고객센터 정보 조회
        Optional<CustomerSupport> optionalCustomerSupport = customerSupportRepository.findById(customerSupportId);
        if (optionalCustomerSupport.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_CUSTOMER_SUPPORT);
        }

        CustomerSupport customerSupport = optionalCustomerSupport.get();

        // 파일 저장 디렉토리 생성
        String uploadDir = "uploads"; // 파일을 저장할 디렉토리 경로
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();  // 디렉토리가 없으면 생성
        }

        try {
            for (MultipartFile file : files) {
                // 파일 유효성 검사
                String extension = getFileExtension(file.getOriginalFilename());
                if (!"jpg".equals(extension) && !"png".equals(extension)) {
                    return ResponseDto.setFailed(ResponseMessage.BAD_REQUEST);
                }

                // 파일 저장
                String fileName = UUID.randomUUID() + "." + extension;
                File saveFile = new File(directory, fileName);
                file.transferTo(saveFile);  // 파일을 서버에 저장

                // 첨부파일 엔티티 생성
                CustomerSupportAttachment attachment = CustomerSupportAttachment.builder()
                        .customerSupport(customerSupport)
                        .customerSupportAttachmentFile("/uploads/" + fileName)
                        .build();

                customerSupportAttachmentRepository.save(attachment);  // 데이터베이스에 저장
                attachments.add(attachment);

            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.FILE_UPLOAD_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, attachments);
    }

    @Override
    public ResponseDto<Void> deleteAttachments(Long customerSupportAttachmentId) {
        try {
            Optional<CustomerSupportAttachment> optionalAttachment = customerSupportAttachmentRepository.findById(customerSupportAttachmentId);
            if (optionalAttachment.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_CUSTOMER_SUPPORT_ATTACHMENT);
            }

            // 첨부파일 경로 확인 후 파일 삭제
            CustomerSupportAttachment attachment = optionalAttachment.get();
            Path filePath = Paths.get(attachment.getCustomerSupportAttachmentFile());
            File file = filePath.toFile();
            if (file.exists()) {
                file.delete();  // 파일 시스템에서 삭제
            }

            // 데이터베이스에서 첨부파일 레코드 삭제
            customerSupportAttachmentRepository.delete(attachment);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }

    // 파일 확장자 추출
    private String getFileExtension(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    }
}