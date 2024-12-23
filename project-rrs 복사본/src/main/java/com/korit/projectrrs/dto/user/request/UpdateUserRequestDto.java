package com.korit.projectrrs.dto.user.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdateUserRequestDto {
    @NotNull
    private String name;
    @NotNull
    private String password;
    @NotNull
    private String confirmPassword;
    @NotNull
    private String phone;
    @NotNull
    private String address;
    @NotNull
    private String addressDetail;
    @NotNull
    private String profileImageUrl;

    private boolean status;
}