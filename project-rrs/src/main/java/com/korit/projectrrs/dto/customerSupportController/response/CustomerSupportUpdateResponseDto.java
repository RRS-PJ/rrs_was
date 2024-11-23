package com.korit.projectrrs.dto.customerSupportController.response;

import com.korit.projectrrs.entity.CustomerSupport;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Data
public class CustomerSupportUpdateResponseDto {
    private Long customerSupportId;
    private String customerSupportTitle;
    private String customerSupportContent;
    private char customerSupportStatus;
    private LocalDate customerSupportCreateAt;
    private char customerSupportCategory;

    public CustomerSupportUpdateResponseDto(CustomerSupport customerSupport){
        this.customerSupportId = customerSupport.getCustomerSupportId();
        this.customerSupportTitle = customerSupport.getCustomerSupportTitle();
        this.customerSupportContent = customerSupport.getCustomerSupportContent();
        this.customerSupportStatus = customerSupport.getCustomerSupportStatus();
        this.customerSupportCategory = getCustomerSupportCategory();
    }
}
