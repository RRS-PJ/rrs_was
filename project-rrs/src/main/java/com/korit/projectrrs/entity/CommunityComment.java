package com.korit.projectrrs.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "COMMUNITY_COMMENTS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunityComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMUNITY_COMMENTS_ID", nullable = false, updatable = false)
    private Long communityCommentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMMUNITY_ID", nullable = false)
    private Community community;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(name = "COMMUNITY_COMMENTS_CONTENTS", nullable = false, length = 255)
    private String communityCommentContent;

    // 헬퍼 메서드 추가: userId 값을 반환
    public String getNickname() {
        return user != null ? user.getNickname() : null;
    }

    // 헬퍼 메서드 추가: userId 값을 반환
    public Long getUserId() {
        return user != null ? user.getUserId() : null;
    }

}


