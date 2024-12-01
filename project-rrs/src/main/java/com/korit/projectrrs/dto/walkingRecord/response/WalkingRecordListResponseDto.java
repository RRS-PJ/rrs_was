package com.korit.projectrrs.dto.walkingRecord.response;

import com.korit.projectrrs.entity.WalkingRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalkingRecordListResponseDto {
    private String petProfileImageUrl;
    private Integer walkingRecordDistance;
    private Integer walkingRecordWalkingTime;

    public WalkingRecordListResponseDto(WalkingRecord walkingRecord) {
        this.petProfileImageUrl = walkingRecord.getPetProfile().getPetProfileImageUrl();
        this.walkingRecordDistance = walkingRecord.getWalkingRecordDistance();
        this.walkingRecordWalkingTime = walkingRecord.getWalkingRecordWalkingTime();
    }
}
