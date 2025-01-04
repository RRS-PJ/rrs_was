package com.korit.projectrrs.dto.community.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class CommunityUpdateRequestDto {

    private String communityTitle; // 게시글 제목

    private String communityContent; // 게시글 내용

    private MultipartFile communityThumbnailUrl; // 썸네일 URL (선택 사항)

    // 업로드할 파일 목록을 저장하는 필드
    private List<MultipartFile> attachments;

    // 파일 목록을 반환하는 메서드
    public List<MultipartFile> getFiles() {
        return attachments;
    }

    public MultipartFile getCommunityThumbnailFile() {
        return communityThumbnailUrl;
    }

}
