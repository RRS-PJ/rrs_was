package com.korit.projectrrs.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    String UploadProfileImg(MultipartFile file);
}
