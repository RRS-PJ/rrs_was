package com.korit.projectrrs.dto.customerSupportController.request;

import lombok.Data;

import java.time.LocalTime;

@Data
public class CustomerSupportPostRequestDto {
    private Long customerSupportId;
    private String userId;
    private String customerSupportTitle;
    private String customerSupportContent;
    private String customerSupportStatus;
    private LocalTime customerSupportCreateAt;
    private char customerSupportCategory;
}
