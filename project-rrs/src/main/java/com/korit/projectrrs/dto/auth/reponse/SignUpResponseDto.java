package com.korit.projectrrs.dto.auth.reponse;


import com.korit.projectrrs.entity.User;
import lombok.Getter;

@Getter
public class SignUpResponseDto {
    private Long id;
    private String userNickName;

    public SignUpResponseDto(User user) {
        this.id = user.getId();
        this.userNickName = user.getUserNickName();
    }
}