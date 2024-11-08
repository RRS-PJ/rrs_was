package com.korit.projectrrs.service.implement;


import com.korit.projectrrs.common.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.auth.reponse.LoginResponseDto;
import com.korit.projectrrs.dto.auth.reponse.SignUpResponseDto;
import com.korit.projectrrs.dto.auth.request.LoginRequestDto;
import com.korit.projectrrs.dto.auth.request.SignUpRequestDto;
import com.korit.projectrrs.entity.User;
import com.korit.projectrrs.provider.JwtProvider;
import com.korit.projectrrs.repositoiry.UserRepository;
import com.korit.projectrrs.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptpasswordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public ResponseDto<SignUpResponseDto> singUp(@Valid SignUpRequestDto dto) {
        String userId = dto.getUserId();
        String password = dto.getPassword();
        String confirmPassword = dto.getConfirmPassword();
        String name = dto.getName();
        String email = dto.getEmail();
        String phone = dto.getPhone();
        String gender = dto.getGender();

        SignUpResponseDto data = null;

        // 1. 유효성 검사 //
        if( (userId == null || userId.isEmpty())) {
            // INVALID_USER_ID
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL);
        }

        if (password == null || password.isEmpty() || confirmPassword == null || confirmPassword.isEmpty()) {
            // INVALID_PASSWORD
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL);
        }

        if (!password.equals(confirmPassword)) {
            return ResponseDto.setFailed(ResponseMessage.NOT_MATCH_PASSWORD);
        }

        if (password.length() < 8 || !password.matches(".*[A-Z.*]]") || !password.matches(".*\\d.*")) {
            // .*[A-Z.*] : 하나 이상의 대문자 사용
            // .*\d.* : 하나 이상의 숫자를 포함

            // WEAK_PASSWORD
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL);
        }

        if (name == null || name.isEmpty()) {
            // INVALID_NAME
            return ResponseDto.setFailed((ResponseMessage.VALIDATION_FAIL));
        }

        if (email == null || email.isEmpty() || !EmailValidator.getInstance().isValid(email)) {
            // INVALID_NAME
            return ResponseDto.setFailed((ResponseMessage.VALIDATION_FAIL));
        }

        if (phone == null || phone.isEmpty() || !phone.matches("[0-9]{10,15}$")) {
            // [0-9]{10,15}$ : 10자에서 15자 사이의 숫자로만 이루어짐

            // INVALID_PHONE
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL);
        }

        if (gender != null && !gender.matches("M|F")) {
            // INVALID_GENDER
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL);
        }

        // 2. 중복 체크 //
        if (userRepository.existsByUserId(userId)) {
            return ResponseDto.setFailed((ResponseMessage.EXIST_USER));
        }

        if (userRepository.existsByEmail(email)) {
            return ResponseDto.setFailed((ResponseMessage.EXIST_USER));
        }

        try {
            String encodedPassword = bCryptpasswordEncoder.encode(password);
            User user = User.builder()
                    .userId(userId)
                    .password(encodedPassword)
                    .email(email)
                    .name(name)
                    .phone(phone)
                    .gender(gender)
                    .build();

            userRepository.save(user);

            data = new SignUpResponseDto(user);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<LoginResponseDto> login(LoginRequestDto dto) {
        String userId = dto.getUserId();
        String password = dto.getPassword();

        LoginResponseDto data = null;

        // 1. 유효성 검사 //
        if (userId == null || userId.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL);
        }

        if (password == null || password.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL);
        }

        try {
            User user = userRepository.findByUserId(userId)
                    .orElse(null);

            if (user == null) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);
            }

            if (!bCryptpasswordEncoder.matches(password, user.getPassword())) {
                return ResponseDto.setFailed((ResponseMessage.NOT_MATCH_PASSWORD));
            }

            String token = jwtProvider.generateJwtToken(userId);
            int exprTime = jwtProvider.getExpiration();

            data = new LoginResponseDto(user, token, exprTime);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
