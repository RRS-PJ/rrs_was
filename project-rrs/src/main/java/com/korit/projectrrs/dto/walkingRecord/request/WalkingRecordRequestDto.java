package com.korit.projectrrs.dto.walkingRecord.request;

import com.korit.projectrrs.entity.WalkingRecordWeatherState;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class WalkingRecordRequestDto {
    @NotNull
    @Enumerated(EnumType.STRING)
    private WalkingRecordWeatherState walkingRecordWeatherState;
    @NotNull
    private Integer walkingRecordDistance;

    private Integer walkingRecordWalkingHours;
    private Integer walkingRecordWalkingMinutes;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate walkingRecordCreateAt;
    private String walkingRecordMemo;
    private List<MultipartFile> files;
}
