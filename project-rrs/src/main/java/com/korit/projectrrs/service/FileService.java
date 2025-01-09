package com.korit.projectrrs.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String uploadFile(MultipartFile file, String path);
    void removeFile(String attachmentPath);

}