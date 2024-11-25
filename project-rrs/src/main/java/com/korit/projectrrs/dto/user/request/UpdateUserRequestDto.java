package com.korit.projectrrs.dto.user.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UpdateUserRequestDto {
    private String userName;
    private String userPassword;
    private String confirmPassword;
    private String userPhone;
    private String userAddress;
    private String userAddressDetail;
    @NotBlank
    private String userProfileImageUrl;
}
