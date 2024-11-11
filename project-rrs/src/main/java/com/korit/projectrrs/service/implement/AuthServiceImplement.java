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
        String userName = dto.getUserName();
        String userId = dto.getUserId();
        String userPassword = dto.getUserPassword();
        String confirmPassword = dto.getConfirmPassword();
        String userNickName = dto.getUserNickName();
        String userPhone = dto.getUserPhone();
        String userAddress = dto.getUserAddress();
        String userAddressDetail = dto.getUserAddressDetail();
        String userEmail = dto.getUserEmail();
        String userProfileUrl = dto.getUserProfileImageUrl();

        SignUpResponseDto data = null;

        // 1. 유효성 검사 //
        if (userName == null || userName.isEmpty() || userName.matches("[가-힣]+$")) {
            // INVALID_NAME
            return ResponseDto.setFailed((ResponseMessage.VALIDATION_FAIL));
        }

        if(userId == null || userId.isEmpty() || userId.matches("[a-zA-Z0-9]{5,15}$")) {
            // INVALID_USER_ID
            return ResponseDto.setFailed(ResponseMessage.INVALID_USER_ID);
        }

        if (userPassword == null || userPassword.isEmpty() || confirmPassword == null || confirmPassword.isEmpty()) {
            // INVALID_PASSWORD
            return ResponseDto.setFailed(ResponseMessage.INVALID_USER_PASSWORD);
        }

        if (!userPassword.equals(confirmPassword)) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_CONFIRM_PASSWORD);
        }

        if (userPassword.length() < 8 || !userPassword.matches("(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_\\-+=])[A-Za-z\\d!@#$%^&*()_\\-+=]{8,15}$")) {
            // WEAK_PASSWORD
            return ResponseDto.setFailed(ResponseMessage.INVALID_USER_PASSWORD);
        }

        if (userNickName == null || userNickName.isEmpty() || !userNickName.matches("[a-zA-Z0-9]{2,10}$")) {
            // INVALID_NICKNAME
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL);
        }


        if (userPhone == null || userPhone.isEmpty() || !userPhone.matches("\\d{3}-\\d{3,4}-\\d{4}$")) {
            // [0-9]{10,15}$ : 10자에서 15자 사이의 숫자로만 이루어짐

            // INVALID_PHONE
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL);
        }

        if (userAddress == null || userAddress.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL);
        }

        if (userAddressDetail == null || userAddressDetail.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL);
        }

        if (userEmail == null || userEmail.isEmpty() || !EmailValidator.getInstance().isValid(userEmail) || !userEmail.matches("[A-Za-z-0-9\\-\\.]+@[A-Ja-z-0-9\\-\\.]+\\.[A-Ja-z-0-9]+$")) {
            // INVALID_NAME
            return ResponseDto.setFailed((ResponseMessage.VALIDATION_FAIL));
        }

        if (!userProfileUrl.matches("^(https?:\\/\\/)?(www\\.)?([a-zA-Z0-9_-]+)+(\\.[a-zA-Z]{2,})+(\\/[a-zA-Z0-9&%_\\-./=]+)*\\.(jpg|jpeg|png|gif|bmp)$")){
            return ResponseDto.setFailed(ResponseMessage.INVALID_IMAGE_URL);
        }

        // 2. 중복 체크 //
        if (userRepository.existsByUserId(userId)) {
            return ResponseDto.setFailed((ResponseMessage.EXIST_USER));
        }

        if (userRepository.existsByEmail(userEmail)) {
            return ResponseDto.setFailed((ResponseMessage.EXIST_USER));
        }

        try {
            String encodedPassword = bCryptpasswordEncoder.encode(userPassword);
            User user = User.builder()
                    .userId(userId)
                    .userPassword(encodedPassword)
                    .userNickName(userNickName)
                    .userPhone(userPhone)
                    .userAddress(userAddress)
                    .userAddressDetail(userAddressDetail)
                    .userEmail(userEmail)
                    .userProfileImageUrl(userProfileUrl)
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
        String userPassword = dto.getUserPassword();

        LoginResponseDto data = null;

        // 1. 유효성 검사 //
        if (userId == null || userId.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL);
        }

        if (userPassword == null || userPassword.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.VALIDATION_FAIL);
        }

        try {
            User user = userRepository.findByUserId(userId)
                    .orElse(null);

            if (user == null) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);
            }

            if (!bCryptpasswordEncoder.matches(userPassword, user.getUserPassword())) {
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