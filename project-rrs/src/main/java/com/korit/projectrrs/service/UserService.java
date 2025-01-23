package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.user.request.UpdatePasswordRequestDto;
import com.korit.projectrrs.dto.user.request.UpdateUserRequestDto;
import com.korit.projectrrs.dto.user.response.UserResponseDto;
import jakarta.validation.Valid;

public interface UserService {
    ResponseDto<UserResponseDto> getUserInfo(Long userId);
    ResponseDto<UserResponseDto> updateUser(Long userId, @Valid UpdateUserRequestDto dto);
    ResponseDto<Void> deleteUser(Long userId, String inputPassword);
    ResponseDto<UserResponseDto> updatePassword(Long userId, UpdatePasswordRequestDto dto);
}