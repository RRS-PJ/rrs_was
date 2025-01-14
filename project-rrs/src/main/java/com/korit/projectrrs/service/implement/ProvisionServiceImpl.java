package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.common.constant.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.provision.responseDto.ProvisionListResponseDto;
import com.korit.projectrrs.dto.provision.responseDto.ProvisionResponseDto;
import com.korit.projectrrs.repositoiry.UserRepository;
import com.korit.projectrrs.service.ProvisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProvisionServiceImpl implements ProvisionService {

    @Override
    public ResponseDto<List<ProvisionListResponseDto>> getProvisionList(Long providerId) {
        List<ProvisionResponseDto> data =  new ArrayList<>();

        try {
            List<>
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<ProvisionResponseDto> getProvisionInfo(Long providerId, Long reservationId) {
        return null;
    }
}
