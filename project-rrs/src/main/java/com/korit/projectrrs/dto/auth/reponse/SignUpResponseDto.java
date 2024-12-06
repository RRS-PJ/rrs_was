package com.korit.projectrrs.dto.auth.reponse;

import com.korit.projectrrs.entity.User;
import lombok.Getter;

@Getter
public class SignUpResponseDto {
    private String nickname;

    public SignUpResponseDto(User user) {
        this.nickname = user.getNickname();
    }
}