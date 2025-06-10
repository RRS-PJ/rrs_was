package com.korit.projectrrs.dto.useageguide;

import com.korit.projectrrs.entity.UsageGuide;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UsageGuideResponseAllDto {
    private Long guideId;
    private String guideTitle;
    private LocalDateTime guideCreatedAt;

    public UsageGuideResponseAllDto(UsageGuide usageGuide) {
        this.guideId = usageGuide.getGuideId();
        this.guideTitle = usageGuide.getGuideTitle();
        this.guideCreatedAt = usageGuide.getGuideCreatedAt();
    }
}
