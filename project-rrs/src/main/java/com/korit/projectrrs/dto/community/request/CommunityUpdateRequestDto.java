package com.korit.projectrrs.dto.community.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CommunityUpdateRequestDto {

    private String communityTitle;
    private String communityContent;
    private MultipartFile communityThumbnailFile;
    private List<MultipartFile> attachments = new ArrayList<>();
    public List<MultipartFile> getFiles() {
        return attachments != null ? attachments : new ArrayList<>();
    }
    public boolean isValid() {
        return communityTitle != null && !communityTitle.trim().isEmpty()
                && communityContent != null && !communityContent.trim().isEmpty();
    }
}
