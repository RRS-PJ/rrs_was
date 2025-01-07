package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.auth.reponse.LoginResponseDto;
import com.korit.projectrrs.dto.auth.reponse.SignUpResponseDto;
import com.korit.projectrrs.dto.auth.request.LoginRequestDto;
import com.korit.projectrrs.dto.auth.request.SignUpRequestDto;
import com.korit.projectrrs.dto.mail.SendMailRequestDto;
import jakarta.mail.MessagingException;

public interface AuthService {

    ResponseDto<SignUpResponseDto> signUp(SignUpRequestDto dto);
    ResponseDto<LoginResponseDto> login(LoginRequestDto dto);
    ResponseDto<Void> findUserInfoByEmail(SendMailRequestDto dto) throws MessagingException;
    ResponseDto<String> findUsernameByToken(String token);
}
