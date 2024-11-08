package com.korit.projectrrs.dto.auth.reponse;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDto {
   private com.korit.projectrrs.entity.User user;
   private String token;
   private int exprTime;

}
