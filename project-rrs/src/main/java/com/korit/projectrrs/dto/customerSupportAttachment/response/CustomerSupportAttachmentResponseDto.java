package com.korit.projectrrs.dto.customerSupportAttachment.response;

import com.korit.projectrrs.entity.CustomerSupportAttachment;
import lombok.Data;

@Data
public class CustomerSupportAttachmentResponseDto {
    private String customerSupportAttachmentFile;

    public CustomerSupportAttachmentResponseDto(CustomerSupportAttachment customerSupportAttachment){
        this.customerSupportAttachmentFile = customerSupportAttachment.getCustomerSupportAttachmentFile();
    }
}
