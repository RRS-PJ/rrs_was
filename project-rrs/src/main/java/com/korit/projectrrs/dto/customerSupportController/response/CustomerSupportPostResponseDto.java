package com.korit.projectrrs.dto.customerSupportController.response;

import com.korit.projectrrs.entity.CustomerSupport;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CustomerSupportPostResponseDto {
    private Long customerSupportId;
    private String customerSupportTitle;
    private String customerSupportContent;
    private char customerSupportStatus;
    private LocalDate customerSupportCreateAt;
    private char customerSupportCategory;

    public CustomerSupportPostResponseDto(CustomerSupport customerSupport){
        this.customerSupportId = customerSupport.getCustomerSupportId();
        this.customerSupportTitle = customerSupport.getCustomerSupportTitle();
        this.customerSupportContent = customerSupport.getCustomerSupportContent();
        this.customerSupportStatus = customerSupport.getCustomerSupportStatus();
        this.customerSupportCreateAt = customerSupport.getCustomerSupportCreateAt();
        this.customerSupportCategory = customerSupport.getCustomerSupportCategory();
    }
}
