package com.korit.projectrrs.dto.walkingRecord.response;

import com.korit.projectrrs.dto.fileUpload.response.GetFilePathAndName;
import com.korit.projectrrs.entity.WalkingRecord;
import com.korit.projectrrs.entity.WalkingRecordAttachment;
import com.korit.projectrrs.entity.WalkingRecordWeatherState;
import lombok.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class WalkingRecordResponseDto {
    private Long walkingRecordId;
    private WalkingRecordWeatherState walkingRecordWeatherState;
    private Integer walkingRecordDistance;
    private Integer walkingRecordWalkingTime;
    private LocalDate walkingRecordCreateAt;
    private String walkingRecordMemo;
    private List<String> fileName;
    private List<GetFilePathAndName> fileInfos;

    public WalkingRecordResponseDto(WalkingRecord walkingRecord) {
        this.walkingRecordId = walkingRecord.getWalkingRecordId();
        this.walkingRecordWeatherState = walkingRecord.getWalkingRecordWeatherState();
        this.walkingRecordDistance = walkingRecord.getWalkingRecordDistance();
        this.walkingRecordWalkingTime = walkingRecord.getWalkingRecordWalkingTime();
        this.walkingRecordCreateAt = walkingRecord.getWalkingRecordCreateAt();
        this.walkingRecordMemo = walkingRecord.getWalkingRecordMemo();
        this.fileName = walkingRecord.getWalkingRecordAttachments().stream()
                .map(WalkingRecordAttachment::getWalkingRecordAttachmentFile)
                .collect(Collectors.toList());
    }
}
