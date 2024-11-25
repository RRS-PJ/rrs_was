package com.korit.projectrrs.dto.user.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeleteUserRequestDto {
    @NotBlank
    private String userPassword;
}
