package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.customerSupportAttachment.response.CustomerSupportAttachmentResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CustomerSupportAttachmentService {
    ResponseDto<List<CustomerSupportAttachmentResponseDto>> saveAttachments(List<MultipartFile> files, Long customerSupportId);
    ResponseDto<Void> deleteAttachments(Long customerSupportAttachmentId);
}
