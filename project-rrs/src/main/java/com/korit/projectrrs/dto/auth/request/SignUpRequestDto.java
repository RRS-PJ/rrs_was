package com.korit.projectrrs.dto.auth.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignUpRequestDto {
    @NotBlank
    private String userId;
    @NotBlank
    private String password;
    @NotBlank
    private String confirmPassword;
    @NotBlank
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    private String phone;

    private String gender;
}
