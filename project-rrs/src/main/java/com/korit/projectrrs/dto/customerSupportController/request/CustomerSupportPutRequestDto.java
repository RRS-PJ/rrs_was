package com.korit.projectrrs.dto.customerSupportController.request;

import lombok.Data;

@Data
public class CustomerSupportPutRequestDto {
    private String customerSupportTitle;
    private String customerSupportContent;
}
