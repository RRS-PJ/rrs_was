package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface FileAttachmentService {
    ResponseDto<String> UploadFile(MultipartFile file);
    ResponseDto<Void> removeFile(Long attachmentId);
}
