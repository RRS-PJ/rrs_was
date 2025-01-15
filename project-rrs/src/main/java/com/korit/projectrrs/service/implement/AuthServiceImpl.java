package com.korit.projectrrs.service.implement;


import com.korit.projectrrs.common.constant.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.auth.request.SNSLoginRequestDto;
import com.korit.projectrrs.dto.auth.response.DuplicateFieldCheckResponseDto;
import com.korit.projectrrs.dto.auth.response.LoginResponseDto;
import com.korit.projectrrs.dto.auth.response.SignUpResponseDto;
import com.korit.projectrrs.dto.auth.request.LoginRequestDto;
import com.korit.projectrrs.dto.auth.request.SignUpRequestDto;
import com.korit.projectrrs.dto.mail.SendMailRequestDto;
import com.korit.projectrrs.entity.User;
import com.korit.projectrrs.provider.JwtProvider;
import com.korit.projectrrs.repositoiry.UserRepository;
import com.korit.projectrrs.service.AuthService;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.logging.log4j.util.InternalException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.korit.projectrrs.service.MailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptpasswordEncoder;
    private final JwtProvider jwtProvider;
    private final JavaMailSender javaMailSender;
    private final MailService mailService;

    @Override
    public ResponseDto<SignUpResponseDto> signUp(@Valid SignUpRequestDto dto) {
        String name = dto.getName();
        String username = dto.getUsername();
        String password = dto.getPassword();
        String confirmPassword = dto.getConfirmPassword();
        String nickname = dto.getNickname();
        String phone = dto.getPhone();
        String address = dto.getAddress();
        String addressDetail = dto.getAddressDetail();
        String email = dto.getEmail();
        String profileImageUrl = dto.getProfileImageUrl();

        SignUpResponseDto data = null;

        // 1. 유효성 검사 //
        if (name == null || name.isEmpty() || !name.matches("^[가-힣]+$")) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_USER_NAME);
        }

        if (username == null || username.isEmpty() || !username.matches("^[a-zA-Z0-9]{5,15}$")) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_USER_ID);
        }

        if (password == null || password.isEmpty() || confirmPassword == null || confirmPassword.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_USER_PASSWORD);
        }

        if (!password.equals(confirmPassword)) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_CONFIRM_PASSWORD);
        }

        if (!password.matches("(?=.*\\d)(?=.*[!@#$%^&*()_\\-+=])[A-Za-z\\d!@#$%^&*()_\\-+=]{8,15}$")) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_USER_PASSWORD);
        }

        if (nickname == null || nickname.isEmpty() || !nickname.matches("^[a-zA-Z0-9가-힣]{2,10}$")) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_USER_NICKNAME);
        }

        if (phone == null || phone.isEmpty() || !phone.matches("^[0-9]{11}$")) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_USER_PHONE);
        }

        if (address == null || address.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_USER_ADDRESS);
        }

        if (addressDetail == null || addressDetail.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_USER_ADDRESS_DETAIL);
        }

        if (email == null || email.isEmpty() || !EmailValidator.getInstance().isValid(email)
                || !email.matches("^[A-Za-z0-9][A-Za-z0-9._%+-]*@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_USER_EMAIL);
        }

        if (profileImageUrl != null && !profileImageUrl.isEmpty() &&
                !profileImageUrl.matches("(?i).*\\.(jpg|png|jpeg)$")) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_USER_PROFILE);
        }

        // 2. 중복 체크 //
        if (userRepository.existsByUsername((username))) {
            return ResponseDto.setFailed(ResponseMessage.EXIST_USER_ID);
        }

        if (userRepository.existsByNickname((nickname))) {
            return ResponseDto.setFailed(ResponseMessage.EXIST_USER_NICKNAME);
        }

        if (userRepository.existsByPhone((phone))) {
            return ResponseDto.setFailed(ResponseMessage.EXIST_USER_PHONE);
        }

        if (userRepository.existsByEmail(email)) {
            return ResponseDto.setFailed(ResponseMessage.EXIST_USER_EMAIL);
        }

        try {
            String encodedPassword = bCryptpasswordEncoder.encode(password);
            User user = User.builder()
                    .name(name)
                    .username(username)
                    .password(encodedPassword)
                    .nickname(nickname)
                    .phone(phone)
                    .address(address)
                    .addressDetail(addressDetail)
                    .email(email)
                    .profileImageUrl(profileImageUrl != null ? profileImageUrl : "example.jpg")
                    .roles("ROLE_USER")
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
        String username = dto.getUsername();
        String password = dto.getPassword();

        LoginResponseDto data = null;

        try {
            // 3. 사용자 인증 //
            User user = userRepository.findUserByUsername(username)
                    .orElse(null);

            if (user == null) {
                return ResponseDto.setFailed(ResponseMessage.NOT_MATCH_USER_ID);
            }

            if (!bCryptpasswordEncoder.matches(password, user.getPassword())) {
                return ResponseDto.setFailed(ResponseMessage.NOT_MATCH_PASSWORD);
            }

            // 4. 토큰 생성 //
            String token = jwtProvider.generateJwtToken(user.getUserId(), user.getRoles());
            int exprTime = jwtProvider.getExpiration();

            // 5. 응답 데이터 생성 //
            data = new LoginResponseDto(
                    user.getUserId(),
                    user.getName(),
                    user.getUsername(),
                    user.getNickname(),
                    user.getPhone(),
                    user.getAddress(),
                    user.getAddressDetail(),
                    user.getEmail(),
                    user.getProfileImageUrl(),
                    user.getRoles(),
                    user.getProviderIntroduction(),
                    token,
                    exprTime);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        // 6. 성공 응답 반환 //
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Value("${spring.mail.username}")
    private String senderEmail;

    @Override
    public ResponseDto<Void> findUserInfoByEmail(SendMailRequestDto dto) throws MessagingException {
        String username = dto.getUsername();
        String email = dto.getEmail();
        String token = null;
        MimeMessage message = null;
        Long userId = null;

        if (username == null || username.isEmpty()) {
            username = userRepository.findUsernameByEmail(email)
                    .orElseThrow(() -> new InternalException(ResponseMessage.EXIST_USER_EMAIL));
            token = jwtProvider.generateEmailValidToken(email, username);
            message = mailService.createMailForId(email, username, token);
        } else {
            userId = userRepository.findUserIdByUsernameAndEmail(username, email)
                    .orElseThrow(() -> new InternalException(ResponseMessage.NOT_EXIST_USER_ID));
            token = jwtProvider.generateJwtToken(userId);
            message = mailService.createMailForPw(email, username, token);
        }

        try {
            javaMailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.FAIL_TO_SEND_EMAIL);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }

    @Override
    public ResponseDto<String> findUsernameByToken(String token) {
        if (!jwtProvider.isValidToken(token)) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_TOKEN);
        }

        String username = jwtProvider.getUsernameFromJwt(token);

        if (username == null || username.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.NO_EXIST_USER_NAME_IN_TOKEN);
        }

        int visibleLength = 4;
        String visiblePart = username.substring(0, Math.min(visibleLength, username.length()));
        String maskedPart = "*".repeat(Math.max(0, username.length() - visibleLength));
        String maskedUsername = visiblePart + maskedPart;

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, maskedUsername);
    }

    @Override
    public ResponseDto<DuplicateFieldCheckResponseDto> checkUsernameDuplicate(String username) {
        boolean isDuplicate = userRepository.existsByUsername(username);
        DuplicateFieldCheckResponseDto responseDto = DuplicateFieldCheckResponseDto.builder()
                .isDuplicate(isDuplicate)
                .build();
        return ResponseDto.<DuplicateFieldCheckResponseDto>builder()
                .result(!isDuplicate)
                .data(responseDto)
                .build();
    }

    @Override
    public ResponseDto<DuplicateFieldCheckResponseDto> checkNicknameDuplicate(String nickname) {
        boolean isDuplicate = userRepository.existsByNickname(nickname);
        DuplicateFieldCheckResponseDto responseDto = DuplicateFieldCheckResponseDto.builder()
                .isDuplicate(isDuplicate)
                .build();
        return ResponseDto.<DuplicateFieldCheckResponseDto>builder()
                .result(!isDuplicate)
                .data(responseDto)
                .build();
    }

    @Override
    public ResponseDto<DuplicateFieldCheckResponseDto> checkPhoneDuplicate(String phone) {
        boolean isDuplicate = userRepository.existsByPhone(phone);
        DuplicateFieldCheckResponseDto responseDto = DuplicateFieldCheckResponseDto.builder()
                .isDuplicate(isDuplicate)
                .build();
        return ResponseDto.<DuplicateFieldCheckResponseDto>builder()
                .result(!isDuplicate)
                .data(responseDto)
                .build();
    }

    @Override
    public ResponseDto<DuplicateFieldCheckResponseDto> checkEmailDuplicate(String email) {
        boolean isDuplicate = userRepository.existsByEmail(email);
        DuplicateFieldCheckResponseDto responseDto = DuplicateFieldCheckResponseDto.builder()
                .isDuplicate(isDuplicate)
                .build();
        return ResponseDto.<DuplicateFieldCheckResponseDto>builder()
                .result(!isDuplicate)
                .data(responseDto)
                .build();
    }

    @Override
    public ResponseDto<LoginResponseDto> snsLogin(Long userId) {
        LoginResponseDto data = null;

        try {
            // 3. 사용자 인증 //
            User user = userRepository.findById(userId)
                    .orElse(null);

            if (user == null) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);
            }

            String token = jwtProvider.generateJwtToken(userId, user.getRoles());
            int exprTime = jwtProvider.getExpiration();

            // 5. 응답 데이터 생성 //
            data = new LoginResponseDto(
                    user.getUserId(),
                    user.getName(),
                    user.getUsername(),
                    user.getNickname(),
                    user.getPhone(),
                    user.getAddress(),
                    user.getAddressDetail(),
                    user.getEmail(),
                    user.getProfileImageUrl(),
                    user.getRoles(),
                    user.getProviderIntroduction(),
                    token,
                    exprTime);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}