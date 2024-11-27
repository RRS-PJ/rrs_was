package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.entity.CustomerSupportAttachment;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CustomerSupportAttachmentService {
    ResponseDto<List<CustomerSupportAttachment>> saveAttachments(List<MultipartFile> files, Long customerSupportId);
    ResponseDto<Void> deleteAttachments(Long customerSupportAttachmentId);
}
