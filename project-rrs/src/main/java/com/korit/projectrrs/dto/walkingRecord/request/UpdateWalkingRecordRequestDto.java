package com.korit.projectrrs.dto.walkingRecord.request;

import com.korit.projectrrs.entity.PetProfile;
import com.korit.projectrrs.entity.WalkingRecordWeatherState;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@NoArgsConstructor
@Data
public class UpdateWalkingRecordRequestDto {
    private PetProfile petProfile;
    private WalkingRecordWeatherState walkingRecordWeatherState;
    private Integer walkingRecordDistance;
    private LocalTime walkingRecordWalkingTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate walkingRecordCreateAt;
    private String walkingRecordMemo;
    private List<String> walkingRecordAttachments;
}
