package com.korit.projectrrs.dto.mail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendMailRequestDto {
    private String email;
    private String username;
}
