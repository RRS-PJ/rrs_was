package com.korit.projectrrs.dto.user.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdatePasswordRequestDto {
    @NotNull(message = "Password cannot be null")
    @Size(min = 8, max = 15, message = "Password must be between 8 to 15 characters")
    private String password;

    @NotNull(message = "Confirm password cannot be null")
    private String confirmPassword;
}
