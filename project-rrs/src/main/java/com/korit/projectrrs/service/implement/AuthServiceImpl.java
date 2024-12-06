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
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptpasswordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public ResponseDto<SignUpResponseDto> singUp(@Valid SignUpRequestDto dto) {
        String name = dto.getName();
        String userName = dto.getUserName(); // 사용자 실제 id
        String password = dto.getPassword();
        String confirmPassword = dto.getConfirmUserPassword();
        String userNickName = dto.getUserNickName();
        String userPhone = dto.getUserPhone();
        String userAddress = dto.getUserAddress();
        String userAddressDetail = dto.getUserAddressDetail();
        String userEmail = dto.getUserEmail();
        String userProfileImageUrl = dto.getUserProfileImageUrl();


        SignUpResponseDto data = null;

        // 1. 유효성 검사 //
        if (name == null || name.isEmpty() || !name.matches("^[가-힣]+$")) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_NAME);
        }

        if (userName == null || userName.isEmpty() || !userName.matches("^[a-zA-Z0-9]{5,15}$")) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_USER_NAME);
        }

        if (password == null || password.isEmpty() || confirmPassword == null || confirmPassword.isEmpty()) {
            // INVALID_PASSWORD
            return ResponseDto.setFailed(ResponseMessage.INVALID_USER_PASSWORD);
        }

        if (!password.equals(confirmPassword)) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_CONFIRM_PASSWORD);
        }

        if (!password.matches("(?=.*\\d)(?=.*[!@#$%^&*()_\\-+=])[A-Za-z\\d!@#$%^&*()_\\-+=]{8,15}$")) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_USER_PASSWORD);
        }

        if (userNickName == null || userNickName.isEmpty() || !userNickName.matches("^[a-zA-Z0-9가-힣]{2,10}$")) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_USER_NICKNAME);
        }

        if (userPhone == null || userPhone.isEmpty() || !userPhone.matches("^[0-9]{11}$")) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_USER_PHONE);
        }

        if (userAddress == null || userAddress.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_USER_ADDRESS);
        }

        if (userAddressDetail == null || userAddressDetail.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_USER_ADDRESS_DETAIL);
        }

        if (userEmail == null || userEmail.isEmpty() || !EmailValidator.getInstance().isValid(userEmail)
                || !userEmail.matches("^[A-Za-z0-9][A-Za-z0-9._%+-]*@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            return ResponseDto.setFailed((ResponseMessage.INVALID_USER_EMAIL));
        }

        if (userProfileImageUrl != null && !userProfileImageUrl.isEmpty() &&
                !userProfileImageUrl.matches(".*\\.(jpg|png)$")) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_USER_PROFILE);
        }

        // 2. 중복 체크 //
        if (userRepository.existsByUserName(userName)) {
            return ResponseDto.setFailed(ResponseMessage.EXIST_USER_NAME);
        }

        if (userRepository.existsByUserNickName(userNickName)) {
            return ResponseDto.setFailed(ResponseMessage.EXIST_USER_NICKNAME);
        }

        if (userRepository.existsByUserPhone(userPhone)) {
            return ResponseDto.setFailed(ResponseMessage.EXIST_USER_PHONE);
        }

        if (userRepository.existsByUserEmail(userEmail)) {
            return ResponseDto.setFailed(ResponseMessage.EXIST_USER_EMAIL);
        }

        try {
            String encodedPassword = bCryptpasswordEncoder.encode(password);
            User user = User.builder()
                    .name(name)
                    .userName(userName) // 사용자 실제 id
                    .password(encodedPassword)
                    .userNickName(userNickName)
                    .userPhone(userPhone)
                    .userAddress(userAddress)
                    .userAddressDetail(userAddressDetail)
                    .userEmail(userEmail)
                    .userProfileImageUrl(userProfileImageUrl != null ? userProfileImageUrl : "example.jpg")
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
    public ResponseDto<LoginResponseDto> login(@Valid LoginRequestDto dto) {
        // 1. 입력값 추출 //
        String userName = dto.getUserName(); // 실제 사용자 id
        String password = dto.getPassword();

        LoginResponseDto data = null;

        // 2. 유효성 검사 //
        if (userName == null || userName.isEmpty() || !userName.matches("^[a-zA-Z0-9]{5,15}$")) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_USER_NAME);
        }

        if (password == null || password.isEmpty() ||
                password.length() < 8 ||
                !password.matches("(?=.*\\d)(?=.*[!@#$%^&*()_\\-+=])[A-Za-z\\d!@#$%^&*()_\\-+=]{8,15}$")) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_USER_PASSWORD);
        }

        try {
            // 3. 사용자 인증 //
            User user = userRepository.findByUserName(userName)
                    .orElse(null);

            if (user == null) {
                return ResponseDto.setFailed(ResponseMessage.NOT_MATCH_USER_NAME);
            }

            if (!bCryptpasswordEncoder.matches(password, user.getPassword())) {
                return ResponseDto.setFailed(ResponseMessage.NOT_MATCH_PASSWORD);
            }

            // 4. 토큰 생성 //
            String token = jwtProvider.generateJwtToken(user.getUserId());
            int exprTime = jwtProvider.getExpiration();

            // 5. 응답 데이터 생성 //
            data = new LoginResponseDto(user, token, exprTime);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        // 6. 성공 응답 반환 //
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}