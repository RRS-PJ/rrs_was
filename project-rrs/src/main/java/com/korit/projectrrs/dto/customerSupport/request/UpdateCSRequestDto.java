package com.korit.projectrrs.dto.customerSupport.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateCSRequestDto {
    @NotBlank
    private String customerSupportTitle;

    @NotBlank
    private String customerSupportContent;

    @NotBlank
    private char customerSupportCategory;
}
