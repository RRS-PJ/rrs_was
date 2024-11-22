package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.user.request.UpdateUserRequestDto;
import com.korit.projectrrs.dto.user.response.UserResponseDto;

public interface UserService {
    ResponseDto<UserResponseDto> getUserInfo(String userId);
    ResponseDto<UserResponseDto> updateUser(String userId, UpdateUserRequestDto dto);
    ResponseDto<Void> deleteUser(String userId);
}
