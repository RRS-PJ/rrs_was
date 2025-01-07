package com.korit.projectrrs.dto.walkingRecord.request;

import com.korit.projectrrs.entity.WalkingRecordAttachment;
import com.korit.projectrrs.entity.WalkingRecordWeatherState;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Data
public class UpdateWalkingRecordRequestDto {
    private WalkingRecordWeatherState walkingRecordWeatherState;
    private Integer walkingRecordDistance;
    private Integer walkingRecordWalkingTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate walkingRecordCreateAt;
    private String walkingRecordMemo;
    private List<MultipartFile> files;
}
