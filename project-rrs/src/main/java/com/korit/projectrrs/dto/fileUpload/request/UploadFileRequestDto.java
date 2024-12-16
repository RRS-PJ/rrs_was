package com.korit.projectrrs.dto.fileUpload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadFileRequestDto {
    private Long userId;
    private List<MultipartFile> file;
}
