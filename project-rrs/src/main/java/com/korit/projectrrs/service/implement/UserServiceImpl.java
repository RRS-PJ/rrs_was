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
    public ResponseDto<UserResponseDto> getUserInfo(Long userId) {

        UserResponseDto data = null;

        try {
            Optional<User> optionalUser = userRepository.findById(userId);

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
    public ResponseDto<UserResponseDto> updateUser(Long userId, UpdateUserRequestDto dto) {
        String name = dto.getName();
        String password = dto.getPassword();
        String confirmPassword = dto.getConfirmPassword();
        String phone = dto.getPhone();
        String address = dto.getAddress();
        String addressDetail = dto.getAddressDetail();
        String profileImageUrl = dto.getProfileImageUrl();

        UserResponseDto data = null;

        if (name != null && !name.isEmpty() && !name.matches("^[가-힣]+$")) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_USER_NAME);
        }

        if (password != null && !password.isEmpty() && confirmPassword != null && !confirmPassword.isEmpty()) {
            if (!password.equals(confirmPassword)) {
                return ResponseDto.setFailed(ResponseMessage.INVALID_USER_PASSWORD);
            }

            if (!password.matches("(?=.*\\d)(?=.*[!@#$%^&*()_\\-+=])[A-Za-z\\d!@#$%^&*()_\\-+=]{8,15}$")) {
                return ResponseDto.setFailed(ResponseMessage.INVALID_USER_PASSWORD);
            }
        }

        if (phone != null && !phone.isEmpty() && !phone.matches("^[0-9]{11}$")) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_USER_PHONE);
        }

        if (profileImageUrl != null && !profileImageUrl.isEmpty() &&
                !profileImageUrl.matches(".*\\.(jpg|png)$")) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_USER_PROFILE);
        }

        if (userRepository.existsByPhone(phone)) {
            return ResponseDto.setFailed(ResponseMessage.EXIST_USER_PHONE);
        }

        try {
            Optional<User> optionalUser = userRepository.findById(userId);

            if (optionalUser.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);
            }

            User user = optionalUser.get();

            String encodedPassword = user.getPassword();

            if (password != null && !password.isEmpty()) {
                encodedPassword = bCryptpasswordEncoder.encode(password);
            }

            user = user.toBuilder()
                    .name(name != null ? name : user.getName())
                    .password(encodedPassword)
                    .phone(phone != null ? phone : user.getPhone())
                    .address(address != null ? address : user.getAddress())
                    .addressDetail(addressDetail != null ? addressDetail : user.getAddressDetail())
                    .profileImageUrl(
                            (profileImageUrl != null && !profileImageUrl.isEmpty())
                                    ? profileImageUrl
                                    : (profileImageUrl == null && user.getProfileImageUrl() != null)
                                    ? user.getProfileImageUrl()
                                    : "example.jpg"
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
    public ResponseDto<Void> deleteUser(Long userId) {
        try {
            Optional<User> optionalUser = userRepository.findById(userId);
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