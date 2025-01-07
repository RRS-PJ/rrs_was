package com.korit.projectrrs.dto.customerSupport.response;

import com.korit.projectrrs.dto.fileUpload.response.GetFilePathAndName;
import com.korit.projectrrs.entity.CustomerSupport;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class GetCSResponseDto {
    private Long customerSupportId;
    private String customerSupportTitle;
    private String customerSupportContent;
    private char customerSupportCategory;
    private LocalDateTime customerSupportCreateAt;
    private char customerSupportStatus;
    private List<GetFilePathAndName> fileInfos;

    public GetCSResponseDto(CustomerSupport customerSupport){
        this.customerSupportId = customerSupport.getCsId();
        this.customerSupportTitle = customerSupport.getCustomerSupportTitle();
        this.customerSupportContent = customerSupport.getCustomerSupportContent();
        this.customerSupportCategory = customerSupport.getCustomerSupportCategory();
        this.customerSupportCreateAt = customerSupport.getCustomerSupportCreateAt();
        this.customerSupportStatus = customerSupport.getCustomerSupportStatus();
    }
}