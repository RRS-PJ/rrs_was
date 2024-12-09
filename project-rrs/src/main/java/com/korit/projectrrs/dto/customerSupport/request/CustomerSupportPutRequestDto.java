package com.korit.projectrrs.dto.customerSupport.request;

import lombok.Data;

@Data
public class CustomerSupportPutRequestDto {
    private String customerSupportTitle;
    private String customerSupportContent;
    private char customerSupportCategory;
}
