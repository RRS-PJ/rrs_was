package com.korit.projectrrs.dto.community.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * 커뮤니티 게시글 업데이트 요청 DTO
 */
@Getter
@Setter
public class CommunityUpdateRequestDto {

    /**
     * 게시글 제목 (선택 사항)
     */
    private String communityTitle;

    /**
     * 게시글 내용 (선택 사항)
     */
    private String communityContent;

    /**
     * 썸네일 파일 (선택 사항)
     */
    private MultipartFile communityThumbnailFile;

    /**
     * 첨부파일 리스트 (선택 사항, 기본값: 빈 리스트)
     */
    private List<MultipartFile> attachments = new ArrayList<>();

    /**
     * 첨부파일 리스트 반환
     *
     * @return 첨부파일 리스트
     */
    public List<MultipartFile> getFiles() {
        return attachments;
    }

    /**
     * 썸네일 파일 반환
     *
     * @return 썸네일 파일
     */
    public MultipartFile getCommunityThumbnailFile() {
        return communityThumbnailFile;
    }
}
