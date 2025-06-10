package com.korit.projectrrs.dto.auth.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DuplicateFieldCheckResponseDto {
    private boolean isDuplicate; // 중복 여부
}
