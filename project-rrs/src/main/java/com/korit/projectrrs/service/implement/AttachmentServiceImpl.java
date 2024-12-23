package com.korit.projectrrs.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String uploadFile(MultipartFile file) {
        if(file == null) { return null; }

        String newFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        String filePath = "profile/" + newFileName;

        File f = new File(uploadDir, "profile");
        if(!f.exists()) { f.mkdirs(); }

        Path uploadPath = Paths.get(uploadDir + filePath);
        try {
            Files.write(uploadPath, file.getBytes());
        }catch (Exception e) {
            e.printStackTrace();
        }

        return filePath;
    }
}
