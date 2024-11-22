package com.korit.projectrrs.dto.customerSupportController.request;

import lombok.Data;

@Data
public class CustomerSupportGetRequestDto {
    private String customerSupportTitle;
    private String customerSupportContent;
    private char customerSupportCategory;
}
