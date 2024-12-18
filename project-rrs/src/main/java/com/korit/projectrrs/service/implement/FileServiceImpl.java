package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.service.FileService;
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
public class FileServiceImpl implements FileService {
    @Value("${root.path}")
    private String rootPath;

    @Override
    public String uploadFile(MultipartFile file, String path) {
        String filePath = null;

        if(file == null) { return null; }

        String newFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        filePath = "file/" + path + "/" + newFileName;

        File f = new File(rootPath, "file");
        if(!f.exists()) { f.mkdirs(); }

        Path uploadPath = Paths.get(rootPath + filePath);

        try {
            Files.write(uploadPath, file.getBytes());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }

    @Override
    public void removeFile(String filePath) {
        String srcFileName = null;
        File file = new File(rootPath, filePath);

        if (file.exists()) {
            boolean deleted = file.delete();
        }
    }

}