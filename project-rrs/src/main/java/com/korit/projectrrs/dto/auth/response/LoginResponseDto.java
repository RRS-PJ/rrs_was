package com.korit.projectrrs.dto.auth.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDto {
    private Long userId;
    private String name;
    private String username;
    private String nickname;
    private String phone;
    private String address;
    private String addressDetail;
    private String email;
    private String profileImageUrl;
    private String roles;
    private String providerIntroduction;
    private String token;
    private int exprTime;
}