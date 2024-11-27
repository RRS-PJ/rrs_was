package com.korit.projectrrs.dto.customerSupportAttachment.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerSupportAttachmentResponseDto {
    private Long customerSupportAttachmentId;
    private String customerSupportAttachmentFile;
}
