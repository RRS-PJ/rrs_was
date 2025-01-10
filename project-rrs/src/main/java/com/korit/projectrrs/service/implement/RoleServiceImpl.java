package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.common.constant.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.role.requestDto.RoleRequestDto;
import com.korit.projectrrs.entity.User;
import com.korit.projectrrs.repositoiry.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl {
    private final UserRepository userRepository;

    @Transactional
    public ResponseDto<Void> updateProviderRole(Long userId, RoleRequestDto roleDto) {
        Boolean isActive = roleDto.getIsActive();
        String role = ", ROLE_PROVIDER";

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
            }

        } else {
            deleteProviderRole(userId, role);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }

    @Transactional
    public ResponseDto<Void> deleteProviderRole(Long userId, String role) {
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
}

