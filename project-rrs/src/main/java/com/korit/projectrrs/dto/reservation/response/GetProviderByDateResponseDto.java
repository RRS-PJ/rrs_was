package com.korit.projectrrs.dto.reservation.response;

import com.korit.projectrrs.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
public class GetProviderByDateResponseDto {
    private Long providerId;
    public GetProviderByDateResponseDto(User provider){
        this.providerId = provider.getUserId();
    }
}