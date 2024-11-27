package com.korit.projectrrs.dto.walkingRecord.request;

import com.korit.projectrrs.entity.PetProfile;
import com.korit.projectrrs.entity.WalkingRecordWeatherState;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
public class WalkingRecordRequestDto {
    @NotBlank
    private PetProfile petProfile;
    @NotBlank
    private WalkingRecordWeatherState walkingRecordWeatherState;
    @NotBlank
    private Integer walkingRecordDistance;
    @NotBlank
    private LocalTime walkingRecordWalkingTime;
    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate walkingRecordCreateAt;
    private String walkingRecordMemo;
    private List<String> walkingRecordAttachments;
}
