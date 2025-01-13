package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.common.constant.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.role.requestDto.RoleRequestDto;
import com.korit.projectrrs.dto.role.responseDto.RoleResponseDto;
import com.korit.projectrrs.entity.Pet;
import com.korit.projectrrs.entity.User;
import com.korit.projectrrs.repositoiry.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl {
    private final UserRepository userRepository;

    @Transactional
    public ResponseDto<RoleResponseDto> updateProviderRole(Long userId, RoleRequestDto roleDto) {
        Boolean isActive = roleDto.getIsActive();
        String role = ", ROLE_PROVIDER";
        RoleResponseDto data = null;

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException(ResponseMessage.NOT_EXIST_USER_ID));

        String roles = user.getRoles();

        if (isActive) {
            if (roles.equals("ROLE_USER")) {
                roles += role;
                user = user.toBuilder()
                        .roles(roles)
                        .build();
                userRepository.save(user);

                data = new RoleResponseDto(true);
            }
        } else {
            deleteProviderRole(userId, role);
            data = new RoleResponseDto(false);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Transactional
    public ResponseDto<RoleResponseDto> deleteProviderRole(Long userId, String role) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException(ResponseMessage.NOT_EXIST_USER_ID));

        if (user.getRoles().equals("ROLE_USER, ROLE_PROVIDER")) {
            user = user.toBuilder()
                    .roles("ROLE_USER")
                    .build();
            userRepository.save(user);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }

    public ResponseDto<RoleResponseDto> getProviderRole(Long userId) {
        RoleResponseDto data = null;

        try {
            Optional<User> optionalUser = userRepository.findById(userId);

            if (optionalUser.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);
            }

            User user = optionalUser.get();

            String roles = user.getRoles();
            boolean isProviderActive = roles != null && roles.trim().contains("ROLE_PROVIDER");

            data = new RoleResponseDto(isProviderActive);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}

