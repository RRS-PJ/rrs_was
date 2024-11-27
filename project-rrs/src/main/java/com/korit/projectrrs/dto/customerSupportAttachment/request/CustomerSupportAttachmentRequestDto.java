package com.korit.projectrrs.dto.customerSupportAttachment.request;

import com.korit.projectrrs.entity.CustomerSupport;
import lombok.Data;

@Data
public class CustomerSupportAttachmentRequestDto {
    private CustomerSupport customerSupport;
    private String customerSupportAttachmentFile;
}
