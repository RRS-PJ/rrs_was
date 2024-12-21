package com.korit.projectrrs.dto.community.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class CommunityCreateRequestDto {

    @JsonProperty("communityTitle")
    @NotBlank(message = "Community title is required.")
    private String communityTitle;

    @JsonProperty("communityContent")
    @NotBlank(message = "Community content is required.")
    private String communityContent;

    @JsonProperty("communityThumbnailUrl")
    private MultipartFile communityThumbnailUrl;

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
