package com.korit.projectrrs.dto.provider.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.korit.projectrrs.dto.role.requestDto.RoleRequestDto;
import com.korit.projectrrs.dto.role.responseDto.RoleResponseDto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Data
public class ProviderRequestDto {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private List<LocalDate> availableDate;

    @NotNull
    private String providerIntroduction;
}
