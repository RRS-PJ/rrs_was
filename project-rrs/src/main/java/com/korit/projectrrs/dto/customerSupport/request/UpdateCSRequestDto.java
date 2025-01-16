package com.korit.projectrrs.dto.customerSupport.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class UpdateCSRequestDto {
    @NotBlank
    private String customerSupportTitle;

    @NotBlank
    private String customerSupportContent;

    private String existFilePath;

    private List<MultipartFile> files;

    private String path = "inquiry-and-report";
}
