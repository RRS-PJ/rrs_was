package com.korit.projectrrs.dto.auth.reponse;

import com.korit.projectrrs.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDto {
   private User user;
   private String token;
   private int exprTime;
}
