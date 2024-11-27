package com.korit.projectrrs.controller;

import com.korit.projectrrs.common.ApiMappingPattern;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.customerSupportAttachment.response.CustomerSupportAttachmentResponseDto;
import com.korit.projectrrs.entity.CustomerSupportAttachment;
import com.korit.projectrrs.service.CustomerSupportAttachmentService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiMappingPattern.CUSTOMER_SUPPORT_ATTACHMENT)
public class CustomerSupportAttachmentController {

    private final CustomerSupportAttachmentService customerSupportAttachmentService;
    private final String UPLOAD_ATTACHMENT = "/customer-support/{customerSupportId}";
    private final String DELETE_ATTACHMENT = "/customer-support-attachment/{customerSupportAttachmentId}";

    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostMapping(UPLOAD_ATTACHMENT)
    public ResponseEntity<ResponseDto<List<CustomerSupportAttachmentResponseDto>>> saveAttachments(
            @AuthenticationPrincipal String userId,
            @PathVariable Long customerSupportId,
            @RequestParam List<MultipartFile> files
    ) {
        ResponseDto<List<CustomerSupportAttachmentResponseDto>> response =
                customerSupportAttachmentService.saveAttachments(files, customerSupportId);

        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping(DELETE_ATTACHMENT)
    public ResponseEntity<ResponseDto<Void>> deleteAttachments(
            @AuthenticationPrincipal String userId,
            @PathVariable Long customerSupportAttachmentId
    ) {
        ResponseDto<Void> response =
                customerSupportAttachmentService.deleteAttachments(customerSupportAttachmentId);

        HttpStatus status = response.isResult() ? HttpStatus.NO_CONTENT : HttpStatus.FORBIDDEN;
        return ResponseEntity.status(status).body(response);
    }

    @PostConstruct
    public void init() {
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs(); // 디렉토리 생성
        }
    }
}
