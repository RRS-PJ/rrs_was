package com.korit.projectrrs.dto.customerSupport.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreateCSRequestDto {
    @NotBlank
    private String customerSupportTitle;

    @NotBlank
    private String customerSupportContent;

    @NotBlank
    private char customerSupportCategory;

    private List<MultipartFile> files;

    private String path;
}
