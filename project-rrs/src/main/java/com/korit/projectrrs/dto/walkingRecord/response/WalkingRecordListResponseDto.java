package com.korit.projectrrs.dto.walkingRecord.response;

import com.korit.projectrrs.entity.WalkingRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalkingRecordListResponseDto {
    private String petImageUrl;
    private Integer walkingRecordDistance;
    private Integer walkingRecordWalkingTime;

    public WalkingRecordListResponseDto(WalkingRecord walkingRecord) {
        this.petImageUrl = walkingRecord.getPet().getPetImageUrl();
        this.walkingRecordDistance = walkingRecord.getWalkingRecordDistance();
        this.walkingRecordWalkingTime = walkingRecord.getWalkingRecordWalkingTime();
    }
}
