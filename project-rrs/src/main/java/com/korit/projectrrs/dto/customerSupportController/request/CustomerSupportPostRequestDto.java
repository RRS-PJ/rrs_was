package com.korit.projectrrs.dto.customerSupportController.request;

import lombok.Data;

import java.time.LocalTime;

@Data
public class CustomerSupportPostRequestDto {
    private String customerSupportTitle;
    private String customerSupportContent;
    private char customerSupportCategory;
}
