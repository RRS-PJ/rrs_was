package com.korit.projectrrs.dto.auth.reponse;

import com.korit.projectrrs.entity.User;
import lombok.Getter;

@Getter
public class SignUpResponseDto {
    private String userNickName;

    public SignUpResponseDto(User user) {
        this.userNickName = user.getUserNickName();
    }
}