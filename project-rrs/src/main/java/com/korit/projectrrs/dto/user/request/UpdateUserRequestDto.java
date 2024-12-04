package com.korit.projectrrs.dto.user.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdateUserRequestDto {
    private String name;
    private String password;
    private String confirmPassword;
    private String phone;
    private String address;
    private String addressDetail;
    private String profileImageUrl;
}
