package com.korit.projectrrs.dto.customerSupportController.request;

import lombok.Data;

import java.time.LocalTime;

@Data
public class CustomerSupportUpdateRequestDto {
    private String customerSupportTitle;
    private String customerSupportContent;
}
