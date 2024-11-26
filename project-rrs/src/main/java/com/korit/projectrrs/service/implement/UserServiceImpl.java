package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.common.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.user.request.UpdateUserRequestDto;
import com.korit.projectrrs.dto.user.response.UserResponseDto;
import com.korit.projectrrs.entity.User;
import com.korit.projectrrs.repositoiry.UserRepository;
import com.korit.projectrrs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptpasswordEncoder;

    @Override
    public ResponseDto<UserResponseDto> getUserInfo(String userId) {

        UserResponseDto data = null;

        try {
            Optional<User> optionalUser = userRepository.findByUserId(userId);

            if (optionalUser.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);
            }

            User user = optionalUser.get();

            data = new UserResponseDto(user);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<UserResponseDto> updateUser(String userId, UpdateUserRequestDto dto) {
        String userName = dto.getUserName();
        String userPassword = dto.getUserPassword();
        String confirmPassword = dto.getConfirmPassword();
        String userPhone = dto.getUserPhone();
        String userAddress = dto.getUserAddress();
        String userAddressDetail = dto.getUserAddressDetail();
        String userProfileImageUrl = dto.getUserProfileImageUrl();

        UserResponseDto data = null;

        if (userName != null && !userName.isEmpty() && !userName.matches("^[가-힣]+$")) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_USER_NAME);
        }

        if (userPassword != null && !userPassword.isEmpty() && confirmPassword != null && !confirmPassword.isEmpty()) {
            if (!userPassword.equals(confirmPassword)) {
                return ResponseDto.setFailed(ResponseMessage.INVALID_USER_PASSWORD);
            }

            if (!userPassword.matches("(?=.*\\d)(?=.*[!@#$%^&*()_\\-+=])[A-Za-z\\d!@#$%^&*()_\\-+=]{8,15}$")) {
                return ResponseDto.setFailed(ResponseMessage.INVALID_USER_PASSWORD);
            }
        }

        if (userPhone != null && !userPhone.isEmpty() && !userPhone.matches("^[0-9]{11}$")) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_USER_PHONE);
        }

        if (userProfileImageUrl != null && !userProfileImageUrl.isEmpty() &&
                !userProfileImageUrl.matches("^(https?://.*\\.(jpg|png))$")) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_USER_PROFILE);
        }

        if (userRepository.existsByUserPhone(userPhone)) {
            return ResponseDto.setFailed(ResponseMessage.EXIST_USER_PHONE);
        }

        try {
            Optional<User> optionalUser = userRepository.findByUserId(userId);

            if (optionalUser.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);
            }

            User user = optionalUser.get();

            String encodedUserPassword = user.getUserPassword();

            if (userPassword != null && !userPassword.isEmpty()) {
                encodedUserPassword = bCryptpasswordEncoder.encode(userPassword);
            }

            user = user.toBuilder()
                    .userName(userName != null ? userName : user.getUserName())
                    .userPassword(encodedUserPassword)
                    .userPhone(userPhone != null ? userPhone : user.getUserPhone())
                    .userAddress(userAddress != null ? userAddress : user.getUserAddress())
                    .userAddressDetail(userAddressDetail != null ? userAddressDetail : user.getUserAddressDetail())
                    .userProfileImageUrl(
                            // 새로운 이미지 URL이 있을 때
                            (userProfileImageUrl != null && !userProfileImageUrl.isEmpty())
                                    ? userProfileImageUrl  // 새로운 이미지 URL로 설정
                                    : (userProfileImageUrl == null && user.getUserProfileImageUrl() != null)
                                    ? user.getUserProfileImageUrl() // null이면 기존 이미지 유지
                                    : "example.jpg"   // 빈 문자열("")일 경우 기본 이미지로 설정
                    )
                    .build();

            userRepository.save(user);

            data = new UserResponseDto(user);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<Void> deleteUser(String userId) {

        try {
            Optional<User> optionalUser = userRepository.findByUserId(userId);

            if (optionalUser.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);
            }

            User user = optionalUser.get();
            userRepository.delete(user);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }
}

