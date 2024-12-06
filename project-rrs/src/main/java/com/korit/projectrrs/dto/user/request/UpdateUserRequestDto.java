package com.korit.projectrrs.dto.user.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdateUserRequestDto {
    private String userName;
    private String password;
    private String confirmPassword;
    private String userPhone;
    private String userAddress;
    private String userAddressDetail;
    private String userProfileImageUrl;
}
