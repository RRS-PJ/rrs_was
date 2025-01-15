package com.korit.projectrrs.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "communities")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMUNITY_ID", nullable = false, updatable = false)
    private Long communityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(name = "COMMUNITY_TITLE", nullable = false)
    private String communityTitle;

    @Column(name = "COMMUNITY_LIKE_COUNT", nullable = false, columnDefinition = "int unsigned default 0")
    private int communityLikeCount;

    @CreatedDate
    @Column(name = "COMMUNITY_CREATE_AT", nullable = false, updatable = false)
    private LocalDateTime communityCreatedAt;

    @Column(name = "COMMUNITY_UPDATE_AT")
    private LocalDateTime communityUpdatedAt;

    @Column(name = "COMMUNITY_CONTENTS", nullable = false, columnDefinition = "TEXT")
    private String communityContent;

    @Column(name = "COMMUNITY_THUMBNAIL_File")
    private String communityThumbnailFile;

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommunityComment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommunityAttachment> attachments;

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommunityLikes> userLiked = new ArrayList<>();

    public String getNickname() {
        return user != null ? user.getNickname() : null;
    }

    // 좋아요 수 변경
    public void updateLikeCount(int newLikeCount) {
        this.communityLikeCount = newLikeCount;
        // Like count만 변경될 때는 communityUpdatedAt을 갱신하지 않음
    }

    // 다른 필드 변경
    public void updateDetails(String title, String content, String thumbnailFile) {
        this.communityTitle = title;
        this.communityContent = content;
        this.communityThumbnailFile = thumbnailFile;
        this.communityUpdatedAt = LocalDateTime.now(); // 명시적으로 갱신
    }

    @PrePersist
    protected void onCreate() {
        this.communityCreatedAt = LocalDateTime.now();
    }
}