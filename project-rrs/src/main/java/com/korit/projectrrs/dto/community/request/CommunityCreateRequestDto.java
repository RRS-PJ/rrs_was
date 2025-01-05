package com.korit.projectrrs.dto.community.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
public class CommunityCreateRequestDto {

    @NotBlank(message = "Community title is required.")
    private String communityTitle;

    @NotBlank(message = "Community content is required.")
    private String communityContent;

    // 썸네일 파일
    private MultipartFile communityThumbnailFile;

    // 첨부파일 리스트 (기본값: 빈 리스트)
    private List<MultipartFile> attachments = new ArrayList<>();

    /**
     * 첨부파일 리스트 반환
     * @return 첨부파일 리스트
     */
    public List<MultipartFile> getFiles() {
        return attachments;
    }

    /**
     * 썸네일 파일 반환
     * @return 썸네일 파일
     */
    public MultipartFile getCommunityThumbnailFile() {
        return communityThumbnailFile;
    }
}
