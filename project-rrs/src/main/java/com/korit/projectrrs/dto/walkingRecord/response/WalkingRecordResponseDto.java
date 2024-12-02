package com.korit.projectrrs.dto.walkingRecord.response;

import com.korit.projectrrs.dto.walkingRecordAttachment.response.WalkingRecordAttachmentResponseDto;
import com.korit.projectrrs.entity.WalkingRecord;
import com.korit.projectrrs.entity.WalkingRecordAttachment;
import com.korit.projectrrs.entity.WalkingRecordWeatherState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalkingRecordResponseDto {
    private WalkingRecordWeatherState walkingRecordWeatherState;
    private Integer walkingRecordDistance;
    private Integer walkingRecordWalkingTime;
    private LocalDate walkingRecordCreateAt;
    private String walkingRecordMemo;
    private List<WalkingRecordAttachment> walkingRecordAttachments;

    public WalkingRecordResponseDto(WalkingRecord walkingRecord) {
        this.walkingRecordWeatherState = walkingRecord.getWalkingRecordWeatherState();
        this.walkingRecordDistance = walkingRecord.getWalkingRecordDistance();
        this.walkingRecordWalkingTime = walkingRecord.getWalkingRecordWalkingTime();
        this.walkingRecordCreateAt = walkingRecord.getWalkingRecordCreateAt();
        this.walkingRecordMemo = walkingRecord.getWalkingRecordMemo();
        this.walkingRecordAttachments = walkingRecord.getWalkingRecordAttachments();
    }

    public void setAttachments(List<WalkingRecordAttachmentResponseDto> attachmentResponseList) {
        this.walkingRecordAttachments = new ArrayList<>();
    }
}
