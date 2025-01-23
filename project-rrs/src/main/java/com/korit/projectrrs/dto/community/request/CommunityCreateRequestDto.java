package com.korit.projectrrs.dto.community.request;

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
    private MultipartFile communityThumbnailFile;
    private List<MultipartFile> attachments = new ArrayList<>();
    public List<MultipartFile> getFiles() {
        return attachments;
    }
    public MultipartFile getCommunityThumbnailFile() {
        return communityThumbnailFile;
    }
}
