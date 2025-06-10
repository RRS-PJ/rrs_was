package com.korit.projectrrs.dto.communityLike.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ToggleLikeRequestDto {

    @NotNull(message = "communityId는 필수 입력값입니다.")
    private Long communityId;
}
