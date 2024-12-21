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
    private Long commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMMUNITY_ID", nullable = false)
    private Community community;

    @Column(name = "COMMUNITY_COMMENTS_CONTENTS", nullable = false, length = 255)
    private String communityCommentContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    public String getNickname() {return user != null ? user.getNickname() : null;}
}
