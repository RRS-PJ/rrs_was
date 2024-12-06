package com.korit.projectrrs.controller;

import com.korit.projectrrs.common.ApiMappingPattern;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.user.request.UpdateUserRequestDto;
import com.korit.projectrrs.dto.user.response.UserResponseDto;
import com.korit.projectrrs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiMappingPattern.USER)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<ResponseDto<UserResponseDto>> getUserInfo(
            @AuthenticationPrincipal Long userId
    ) {
        ResponseDto<UserResponseDto> response = userService.getUserInfo(userId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping
    public ResponseEntity<ResponseDto<UserResponseDto>> updateUser(
            @AuthenticationPrincipal Long userId,
            @RequestBody UpdateUserRequestDto dto
    ) {
        ResponseDto<UserResponseDto> response = userService.updateUser(userId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping
    public ResponseEntity<ResponseDto<Void>> deleteUser(
            @AuthenticationPrincipal Long userId
    ) {
        ResponseDto<Void> response = userService.deleteUser(userId);
        HttpStatus status = response.isResult() ? HttpStatus.NO_CONTENT : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}
