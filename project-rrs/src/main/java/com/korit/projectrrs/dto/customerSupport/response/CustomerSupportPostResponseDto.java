package com.korit.projectrrs.dto.customerSupport.response;

import com.korit.projectrrs.entity.CustomerSupport;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomerSupportPostResponseDto {
    private String customerSupportTitle;
    private String customerSupportContent;
    private char customerSupportStatus;
    private LocalDateTime customerSupportCreateAt;
    private char customerSupportCategory;

    public CustomerSupportPostResponseDto(CustomerSupport customerSupport){
        this.customerSupportTitle = customerSupport.getCustomerSupportTitle();
        this.customerSupportContent = customerSupport.getCustomerSupportContent();
        this.customerSupportCreateAt = customerSupport.getCustomerSupportCreateAt();
        this.customerSupportCategory = customerSupport.getCustomerSupportCategory();
        this.customerSupportStatus = customerSupport.getCustomerSupportStatus();
    }
}
