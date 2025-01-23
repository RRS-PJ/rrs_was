package com.korit.projectrrs.controller;

import com.korit.projectrrs.common.constant.ApiMappingPattern;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.auth.response.DuplicateFieldCheckResponseDto;
import com.korit.projectrrs.dto.auth.response.LoginResponseDto;
import com.korit.projectrrs.dto.auth.response.SignUpResponseDto;
import com.korit.projectrrs.dto.auth.request.LoginRequestDto;
import com.korit.projectrrs.dto.auth.request.SignUpRequestDto;
import com.korit.projectrrs.dto.mail.SendMailRequestDto;
import com.korit.projectrrs.security.PrincipalUser;
import com.korit.projectrrs.service.AuthService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import static com.korit.projectrrs.common.constant.ApiMappingPattern.*;

@RestController
@RequestMapping(ApiMappingPattern.AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    private static final String SING_UP_PATH= "/sign-up";
    private static final String LOGIN_PATH= "/login";
    private static final String FIND_BY_EMAIL= "/recovery-email";
    private static final String FIND_ID_BY_TOKEN= "/find-id/{token}";
    private static final String SNS_LOGIN= "/sns-login";

    @PostMapping(SING_UP_PATH)
    public ResponseEntity<ResponseDto<SignUpResponseDto>> signUp(@Valid @RequestBody SignUpRequestDto dto) {
        ResponseDto<SignUpResponseDto> response = authService.signUp(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @PostMapping(LOGIN_PATH)
    public ResponseEntity<ResponseDto<LoginResponseDto>> login(@Valid @RequestBody LoginRequestDto dto) {
        ResponseDto<LoginResponseDto> response = authService.login(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).body(response);
    }

    @PostMapping(FIND_BY_EMAIL)
    public ResponseEntity<ResponseDto<Void>> findIdByEmail (@RequestBody SendMailRequestDto dto) throws MessagingException {
        ResponseDto<Void> response = authService.findUserInfoByEmail(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(FIND_ID_BY_TOKEN)
    public ResponseEntity<ResponseDto<String>> findUsernameByToken(@PathVariable String token) {
        ResponseDto<String> response = authService.findUsernameByToken(token);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(DUPLICATE_USERNAME_PATH)
    public ResponseEntity<ResponseDto<DuplicateFieldCheckResponseDto>> checkUsernameDuplicate(@RequestParam String username) {
        ResponseDto<DuplicateFieldCheckResponseDto> response = authService.checkUsernameDuplicate(username);
        return ResponseEntity.ok(response);
    }

    @GetMapping(DUPLICATE_NICKNAME_PATH)
    public ResponseEntity<ResponseDto<DuplicateFieldCheckResponseDto>> checkNicknameDuplicate(@RequestParam String nickname) {
        ResponseDto<DuplicateFieldCheckResponseDto> response = authService.checkNicknameDuplicate(nickname);
        return ResponseEntity.ok(response);
    }

    @GetMapping(DUPLICATE_PHONE_PATH)
    public ResponseEntity<ResponseDto<DuplicateFieldCheckResponseDto>> checkPhoneDuplicate(@RequestParam String phone) {
        ResponseDto<DuplicateFieldCheckResponseDto> response = authService.checkPhoneDuplicate(phone);
        return ResponseEntity.ok(response);
    }

    @GetMapping(DUPLICATE_EMAIL_PATH)
    public ResponseEntity<ResponseDto<DuplicateFieldCheckResponseDto>> checkEmailDuplicate(@RequestParam String email) {
        ResponseDto<DuplicateFieldCheckResponseDto> response = authService.checkEmailDuplicate(email);
        return ResponseEntity.ok(response);
    }

    @PostMapping(SNS_LOGIN)
    public ResponseEntity<ResponseDto<LoginResponseDto>> snsLogin (
            @AuthenticationPrincipal PrincipalUser principalUser
    ){
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<LoginResponseDto> response = authService.snsLogin(userId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).body(response);
    }
}