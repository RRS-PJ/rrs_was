package com.korit.projectrrs.dto.walkingRecord.response;

import com.korit.projectrrs.dto.fileUpload.response.GetFilePathAndName;
import com.korit.projectrrs.entity.WalkingRecord;
import com.korit.projectrrs.entity.WalkingRecordWeatherState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class GetWalkingRecordResponseDto {
    private Long walkingRecordId;
    private WalkingRecordWeatherState walkingRecordWeatherState;
    private Integer walkingRecordDistance;
    private Integer walkingRecordWalkingTime;
    private LocalDate walkingRecordCreateAt;
    private String walkingRecordMemo;
    private List<GetFilePathAndName> fileInfos;

    public GetWalkingRecordResponseDto(WalkingRecord walkingRecord) {
        this.walkingRecordId = walkingRecord.getWalkingRecordId();
        this.walkingRecordWeatherState = walkingRecord.getWalkingRecordWeatherState();
        this.walkingRecordDistance = walkingRecord.getWalkingRecordDistance();
        this.walkingRecordWalkingTime = walkingRecord.getWalkingRecordWalkingTime();
        this.walkingRecordCreateAt = walkingRecord.getWalkingRecordCreateAt();
        this.walkingRecordMemo = walkingRecord.getWalkingRecordMemo();
    }
}
