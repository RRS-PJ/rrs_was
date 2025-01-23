package com.korit.projectrrs.dto.communityLike.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommunityLikeResponseDto {
    private Long communityId;
    private String nickname;
}
