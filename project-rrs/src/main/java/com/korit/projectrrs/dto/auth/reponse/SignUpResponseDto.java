package com.korit.projectrrs.dto.auth.reponse;

import com.korit.projectrrs.entity.User;
import lombok.Getter;

@Getter
public class SignUpResponseDto {
    private Long userId;
    private String userNickName;

    public SignUpResponseDto(User user) {
        this.userId = user.getUserId();
        this.userNickName = user.getUserNickName();
    }
}