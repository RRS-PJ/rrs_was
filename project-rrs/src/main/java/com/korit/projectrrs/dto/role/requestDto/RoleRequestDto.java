package com.korit.projectrrs.dto.role.requestDto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RoleRequestDto {
    @NotNull
    private Boolean isActive;
}