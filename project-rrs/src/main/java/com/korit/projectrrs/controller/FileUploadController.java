package com.korit.projectrrs.controller;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.customerSupport.request.CreateCSRequestDto;
import com.korit.projectrrs.dto.customerSupport.response.CreateCSResponseDto;
import com.korit.projectrrs.dto.fileUpload.request.UploadFileRequestDto;
import com.korit.projectrrs.security.PrincipalUser;
import com.korit.projectrrs.service.CustomerSupportService;
import com.korit.projectrrs.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FileUploadController {
    private final CustomerSupportService customerSupportService;
    private final FileUploadService fileUploadService;

    @CrossOrigin
    @PostMapping("/api/upload")
    public ResponseEntity<?> upload(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @ModelAttribute UploadFileRequestDto uploadFileDto
    ) {
        Long userId = principalUser.getUser().getUserId();
        List<MultipartFile> files = uploadFileDto.getFile();

        CreateCSRequestDto dto = new CreateCSRequestDto();

        ResponseDto<CreateCSResponseDto> response = customerSupportService.createCustomerSupport(userId, dto, uploadFileDto);

        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}