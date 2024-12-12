package com.korit.projectrrs.dto.customerSupport.response;

import com.korit.projectrrs.entity.CustomerSupport;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateCSResponseDto {
    private String customerSupportTitle;
    private String customerSupportContent;
    private char customerSupportCategory;
    private LocalDateTime customerSupportCreateAt;

    public UpdateCSResponseDto(CustomerSupport customerSupport){
        this.customerSupportTitle = customerSupport.getCustomerSupportTitle();
        this.customerSupportContent = customerSupport.getCustomerSupportContent();
        this.customerSupportCategory = customerSupport.getCustomerSupportCategory();
        this.customerSupportCreateAt = customerSupport.getCustomerSupportCreateAt();
    }
}