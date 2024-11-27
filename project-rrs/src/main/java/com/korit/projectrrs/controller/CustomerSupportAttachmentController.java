package com.korit.projectrrs.controller;

import com.korit.projectrrs.common.ApiMappingPattern;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.customerSupportAttachment.response.CustomerSupportAttachmentResponseDto;
import com.korit.projectrrs.entity.CustomerSupport;
import com.korit.projectrrs.entity.CustomerSupportAttachment;
import com.korit.projectrrs.service.CustomerSupportAttachmentService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.Value;
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
    private final String POST_CUSTOMER_SUPPORT_ATTACHMENT = "customer-support/{customerSupportId}";
    private final String DELETE_CUSTOMER_SUPPORT_ATTACHMENT = "customer-support/customerSupportAttachment/{customerSupportAttachmentId}";

    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostMapping(POST_CUSTOMER_SUPPORT_ATTACHMENT)
    private ResponseEntity<ResponseDto<List<CustomerSupportAttachment>>> saveAttachments (
            @AuthenticationPrincipal String userId,
            @PathVariable Long customerSupportId,
            @RequestParam List<MultipartFile> files
    ) {
        ResponseDto<List<CustomerSupportAttachment>> response = customerSupportAttachmentService.saveAttachments(files, customerSupportId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping(DELETE_CUSTOMER_SUPPORT_ATTACHMENT)
    private ResponseEntity<ResponseDto<Void>> deleteAttachments (
            @AuthenticationPrincipal String userId,
            @PathVariable Long customerSupportAttachmentId
    ) {
        ResponseDto<Void> response = customerSupportAttachmentService.deleteAttachments(customerSupportAttachmentId);
        HttpStatus status = response.isResult() ? HttpStatus.NO_CONTENT : HttpStatus.FORBIDDEN;
        return ResponseEntity.status(status).body(response);
    }

    // 디렉토리 초기화
    @PostConstruct
    public void init() {
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();  // 디렉토리 생성
        }
    }
}