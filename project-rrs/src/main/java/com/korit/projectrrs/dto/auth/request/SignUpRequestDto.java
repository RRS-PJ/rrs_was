package com.korit.projectrrs.dto.auth.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDto {
    @NotNull(message = "Name cannot be null")
    @Pattern(regexp = "^[가-힣]+$", message = "Name must only contain Korean characters")
    private String name;

    @NotNull(message = "Username cannot be null")
    @Pattern(regexp = "^[a-zA-Z0-9]{5,15}$", message = "Username must be between 5 to 15 characters")
    private String username;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, max = 15, message = "Password must be between 8 to 15 characters")
    private String password;

    @NotNull(message = "Confirm password cannot be null")
    private String confirmPassword;

    @NotNull(message = "Nickname cannot be null")
    @Pattern(regexp = "^[a-zA-Z0-9가-힣]{2,10}$", message = "Nickname must be between 2 to 10 characters")
    private String nickname;

    @NotNull(message = "Phone cannot be null")
    @Pattern(regexp = "^[0-9]{11}$", message = "Phone number must be 11 digits")
    private String phone;

    @NotNull(message = "Address cannot be null")
    private String address;

    @NotNull(message = "Address detail cannot be null")
    private String addressDetail;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email must be valid")
    private String email;

    private String profileImageUrl;
}