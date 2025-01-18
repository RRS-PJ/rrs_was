package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.common.constant.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.user.request.UpdatePasswordRequestDto;
import com.korit.projectrrs.dto.user.request.UpdateUserRequestDto;
import com.korit.projectrrs.dto.user.response.UserResponseDto;
import com.korit.projectrrs.entity.User;
import com.korit.projectrrs.repositoiry.UserRepository;
import com.korit.projectrrs.service.FileService;
import com.korit.projectrrs.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.InternalException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptpasswordEncoder;
    private final FileService fileService;

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
    public ResponseDto<UserResponseDto> updateUser(Long userId,@Valid UpdateUserRequestDto dto) {
        String name = dto.getName();
        String password = dto.getPassword();
        String confirmPassword = dto.getConfirmPassword();
        String phone = dto.getPhone();
        String address = dto.getAddress();
        String addressDetail = dto.getAddressDetail();
        MultipartFile profileImage = dto.getProfileImageUrl();

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

        if (phone != null && (phone.isEmpty() || !phone.matches("^[0-9]{11}$"))) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_USER_PHONE);
        }

        if (profileImage != null && !profileImage.isEmpty() &&
                !profileImage.getOriginalFilename().matches("(?i).*\\.(jpg|png|jpeg)$")) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_USER_PROFILE);
        }

        try {
            Optional<User> optionalUser = userRepository.findById(userId);

            if (optionalUser.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);
            }

            User user = optionalUser.get();

            if (phone != null && !phone.equals(user.getPhone()) && userRepository.existsByPhone(phone)) {
                return ResponseDto.setFailed(ResponseMessage.EXIST_USER_PHONE);
            }

            boolean isSame = (name == null || name.equals(user.getName())) &&
                    (phone == null || phone.equals(user.getPhone())) &&
                    (address == null || address.equals(user.getAddress())) &&
                    (addressDetail == null || addressDetail.equals(user.getAddressDetail())) &&
                    (profileImage == null || profileImage.getOriginalFilename().equals(user.getProfileImageUrl())) &&
                    (password == null || password.isEmpty());

            if (isSame) {
                return ResponseDto.setFailed(ResponseMessage.NO_MODIFIED_VALUES);
            }

            String encodedPassword = user.getPassword();

            if (password != null && !password.isEmpty()) {
                encodedPassword = bCryptpasswordEncoder.encode(password);
            }

            String profileImageUrl = user.getProfileImageUrl();
            String defaultProfileImageUrl = "images/default-profile.png";

            if (profileImage != null && !profileImage.isEmpty()) {
                String filePath = fileService.uploadFile(profileImage, "profileImage");
                if (filePath != null) {
                    profileImageUrl = filePath;
                }
            } else if (profileImageUrl == null || profileImageUrl.isEmpty()) {
                profileImageUrl = defaultProfileImageUrl;
            }

            user = user.toBuilder()
                    .name(name != null ? name : user.getName())
                    .password(encodedPassword)
                    .phone(phone != null ? phone : user.getPhone())
                    .address(address != null ? address : user.getAddress())
                    .addressDetail(addressDetail != null ? addressDetail : user.getAddressDetail())
                    .profileImageUrl(profileImageUrl)
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
    public ResponseDto<Void> deleteUser(Long userId, String inputPassword) {
        try {
            Optional<User> optionalUser = userRepository.findById(userId);

            if (optionalUser.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);
            }

            User user = optionalUser.get();

            if (!bCryptpasswordEncoder.matches(inputPassword, user.getPassword())) {
                return ResponseDto.setFailed(ResponseMessage.NOT_MATCH_PASSWORD);
            }

            userRepository.delete(user);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }

    @Override
    public ResponseDto<UserResponseDto> updatePassword(Long userId, UpdatePasswordRequestDto dto) {
        String newPassword = dto.getNewPassword();
        String confirmPassword = dto.getConfirmPassword();
        UserResponseDto data = null;

        if (newPassword != null && !newPassword.isEmpty() && confirmPassword != null && !confirmPassword.isEmpty()) {

            if (!newPassword.equals(confirmPassword)) {
                return ResponseDto.setFailed(ResponseMessage.INVALID_USER_PASSWORD);
            }

            if (!newPassword.matches("(?=.*\\d)(?=.*[!@#$%^&*()_\\-+=])[A-Za-z\\d!@#$%^&*()_\\-+=]{8,15}$")) {
                return ResponseDto.setFailed(ResponseMessage.INVALID_USER_PASSWORD);
            }
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new InternalException(ResponseMessage.NOT_EXIST_USER));

        String encodedPassword = user.getPassword();
        if (newPassword != null && !newPassword.isEmpty()) {
            encodedPassword = bCryptpasswordEncoder.encode(newPassword);
        }
        user = user.toBuilder()
                .password(encodedPassword)
                .build();

        userRepository.save(user);
        data = new UserResponseDto(user);
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}