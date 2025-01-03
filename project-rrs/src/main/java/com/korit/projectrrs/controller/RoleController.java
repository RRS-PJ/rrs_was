package com.korit.projectrrs.controller;

import com.korit.projectrrs.common.ApiMappingPattern;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.role.requestDto.RoleRequestDto;
import com.korit.projectrrs.security.PrincipalUser;
import com.korit.projectrrs.service.implement.RoleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiMappingPattern.ROLE)
@RequiredArgsConstructor
public class RoleController {

    private final RoleServiceImpl roleService;

    @PutMapping
    public ResponseEntity<ResponseDto<Void>> updateProviderRole(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestBody RoleRequestDto dto
    ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<Void> response = roleService.updateProviderRole(userId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }
}
