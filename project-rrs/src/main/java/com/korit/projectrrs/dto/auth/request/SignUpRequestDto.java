package com.korit.projectrrs.dto.auth.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignUpRequestDto {
    @NotBlank
    private String userName;
    @NotBlank
    private String userId;
    @NotBlank
    private String userPassword;
    @NotBlank
<<<<<<< HEAD
    private String ConfirmUserPassword;
=======
    private String confirmUserPassword;
>>>>>>> develop
    @NotBlank
    private String userNickName;
    @NotBlank
    private String userPhone;
    @NotBlank
    private String userAddress;
    @NotBlank
    private String userAddressDetail;
    @NotBlank
    private String userEmail;

    private String userProfileImageUrl;
<<<<<<< HEAD
=======

>>>>>>> develop
}