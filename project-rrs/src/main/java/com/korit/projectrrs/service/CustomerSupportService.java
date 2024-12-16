package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.customerSupport.request.CreateCSRequestDto;
import com.korit.projectrrs.dto.customerSupport.request.UpdateCSRequestDto;
import com.korit.projectrrs.dto.customerSupport.response.GetCSResponseDto;
import com.korit.projectrrs.dto.customerSupport.response.CreateCSResponseDto;
import com.korit.projectrrs.dto.customerSupport.response.UpdateCSResponseDto;
import com.korit.projectrrs.dto.fileUpload.request.UploadFileRequestDto;
import com.korit.projectrrs.dto.fileUpload.response.FileUploadResponseDto;

import java.util.List;

public interface CustomerSupportService {
    ResponseDto<CreateCSResponseDto> createCustomerSupport(Long userId, CreateCSRequestDto dto, UploadFileRequestDto uploadFileDto);
    ResponseDto<GetCSResponseDto> getCSByUserIdAndCustomerId(Long userId, Long customerSupportId);
    ResponseDto<List<GetCSResponseDto>> getAllCSByUserId(Long userId);
    ResponseDto<UpdateCSResponseDto> updateCS(Long userId, Long customerSupportId, UpdateCSRequestDto dto);
    ResponseDto<Void> deleteCS(Long userId, Long customerSupportId);
}
