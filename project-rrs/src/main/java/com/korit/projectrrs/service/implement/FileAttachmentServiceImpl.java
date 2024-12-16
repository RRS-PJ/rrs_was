package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.common.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.entity.CustomerSupportAttachment;
import com.korit.projectrrs.repositoiry.CustomerSupportAttachmentRepository;
import com.korit.projectrrs.service.FileAttachmentService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.InternalException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileAttachmentServiceImpl implements FileAttachmentService {
    private final CustomerSupportAttachmentRepository customerSupportAttachmentRepository;

    @Value("${root.path}")
    private String rootPath;

    @Override
    public ResponseDto<String> uploadCSFile(MultipartFile file) {
        String filePath = null;

        if(file == null) { return null; }

        String newFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        filePath = "file/" + newFileName;

        File f = new File(rootPath, "file");
        if(!f.exists()) { f.mkdirs(); }

        Path uploadPath = Paths.get(rootPath + filePath);
        try {
            Files.write(uploadPath, file.getBytes());
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.FILE_UPLOAD_FAILED);
        }
        CustomerSupportAttachment attachment = CustomerSupportAttachment.builder()
                .customerAttachmentFile(filePath)
                .build();
        customerSupportAttachmentRepository.save(attachment);

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, filePath);
    }

    @Override
    public ResponseDto<Void> removeCSFile(Long attachmentId) {
        String srcFileName = null;

        CustomerSupportAttachment attachment = customerSupportAttachmentRepository.findById(attachmentId)
                .orElseThrow(() -> new InternalException(ResponseMessage.FILE_NOT_FOUND));

        String fileName = attachment.getCustomerAttachmentFile();

        String decodedFileName = URLDecoder.decode(fileName, StandardCharsets.UTF_8);
        File file = new File(rootPath, decodedFileName);

        if (file.exists()) {
            boolean deleted = file.delete();
            if (!deleted) {
                return ResponseDto.setFailed(ResponseMessage.FILE_DELETE_FAILED);
            }
        } else {
            return ResponseDto.setFailed(ResponseMessage.FILE_NOT_FOUND);
        }

        customerSupportAttachmentRepository.delete(attachment);

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }
}