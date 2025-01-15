package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.auth.response.DuplicateFieldCheckResponseDto;
import com.korit.projectrrs.dto.auth.response.LoginResponseDto;
import com.korit.projectrrs.dto.auth.response.SignUpResponseDto;
import com.korit.projectrrs.dto.auth.request.LoginRequestDto;
import com.korit.projectrrs.dto.auth.request.SignUpRequestDto;
import com.korit.projectrrs.dto.mail.SendMailRequestDto;
import jakarta.mail.MessagingException;

public interface AuthService {
    ResponseDto<DuplicateFieldCheckResponseDto> checkUsernameDuplicate(String username);
    ResponseDto<DuplicateFieldCheckResponseDto> checkNicknameDuplicate(String nickname);
    ResponseDto<DuplicateFieldCheckResponseDto> checkPhoneDuplicate(String phone);
    ResponseDto<DuplicateFieldCheckResponseDto> checkEmailDuplicate(String email);

    ResponseDto<SignUpResponseDto> signUp(SignUpRequestDto dto);
    ResponseDto<LoginResponseDto> login(LoginRequestDto dto);
    ResponseDto<Void> findUserInfoByEmail(SendMailRequestDto dto) throws MessagingException;
    ResponseDto<String> findUsernameByToken(String token);

    ResponseDto<LoginResponseDto> snsLogin(Long userId);
}
