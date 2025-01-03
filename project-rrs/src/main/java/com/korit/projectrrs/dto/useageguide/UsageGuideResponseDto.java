package com.korit.projectrrs.dto.useageguide;

import com.korit.projectrrs.entity.UsageGuide;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UsageGuideResponseDto {
    private Long guideId;
    private String guideTitle;
    private String guideContent;
    private LocalDateTime guideCreatedAt;

    public UsageGuideResponseDto(UsageGuide usageGuide) {
        this.guideId = usageGuide.getGuideId();
        this.guideTitle = usageGuide.getGuideTitle();
        this.guideContent = usageGuide.getGuideContent();
        this.guideCreatedAt = usageGuide.getGuideCreatedAt();
    }
}
