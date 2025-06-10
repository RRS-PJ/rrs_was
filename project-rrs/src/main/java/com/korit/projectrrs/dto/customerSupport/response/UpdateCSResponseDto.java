package com.korit.projectrrs.dto.customerSupport.response;

import com.korit.projectrrs.entity.CustomerSupport;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class UpdateCSResponseDto {
    private String customerSupportTitle;
    private String customerSupportContent;
    private LocalDateTime customerSupportCreateAt;
    private List<String> files;

    public UpdateCSResponseDto(CustomerSupport customerSupport, List<String> files){
        this.customerSupportTitle = customerSupport.getCustomerSupportTitle();
        this.customerSupportContent = customerSupport.getCustomerSupportContent();
        this.customerSupportCreateAt = customerSupport.getCustomerSupportCreateAt();
        this.files = files;
    }
}