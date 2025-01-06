package com.korit.projectrrs.dto.communityLike.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommunityLikeResponseDto {
    private Long communityId; // 커뮤니티 ID
    private String nickname;  // 사용자 닉네임
}
