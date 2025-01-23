package com.korit.projectrrs.controller;

import com.korit.projectrrs.common.constant.ApiMappingPattern;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.user.request.UpdatePasswordRequestDto;
import com.korit.projectrrs.dto.user.request.UpdateUserRequestDto;
import com.korit.projectrrs.dto.user.response.UserResponseDto;
import com.korit.projectrrs.security.PrincipalUser;
import com.korit.projectrrs.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping(ApiMappingPattern.USER)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final String USER_GET = "/me";
    private final String USER_PUT = "/me";
    private final String USER_DELETE = "/me";
    private final String UPDATE_PASSWORD = "/me/password";

    @GetMapping(USER_GET)
    public ResponseEntity<ResponseDto<UserResponseDto>> getUserInfo(
            @AuthenticationPrincipal PrincipalUser principalUser
    ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<UserResponseDto> response = userService.getUserInfo(userId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping(USER_PUT)
    public ResponseEntity<ResponseDto<UserResponseDto>> updateUser(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @Valid @ModelAttribute UpdateUserRequestDto dto
    ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<UserResponseDto> response = userService.updateUser(userId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping(USER_DELETE)
    public ResponseEntity<ResponseDto<Void>> deleteUser(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestBody Map<String, String> requestBody
    ) {
        String inputPassword = requestBody.get("password");
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<Void> response = userService.deleteUser(userId, inputPassword);
        HttpStatus status = response.isResult() ? HttpStatus.NO_CONTENT : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @PostMapping(UPDATE_PASSWORD)
    public ResponseEntity<ResponseDto<UserResponseDto>> updatePassword(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestBody UpdatePasswordRequestDto dto
    ) {
        System.out.println(principalUser);
        System.out.println(dto);
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<UserResponseDto> response = userService.updatePassword(userId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}