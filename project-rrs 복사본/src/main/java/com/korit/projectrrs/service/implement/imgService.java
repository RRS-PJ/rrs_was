//package com.korit.projectrrs.service.implement;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestPart;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.UUID;
//
//public class imgService {
//    @Value("${file.path}")
//    private String filePath;
//
//    @PostMapping("/img")
//    public ResponseEntity<?> insert(@RequestPart MultipartFile file) throws IOException {
//        System.out.println(file.getOriginalFilename());
//        System.out.println(file.getBytes());
//
//        String originalFilename = file.getOriginalFilename();
//        String newFilename = UUID.randomUUID().toString() + "_" + originalFilename;
//
//        Path uploadPath = Paths.get(filePath, "test/" + newFilename);
//
//        File f = new File(filePath + "test");
//        if(!f.exists())
//    }
//
//
//}
