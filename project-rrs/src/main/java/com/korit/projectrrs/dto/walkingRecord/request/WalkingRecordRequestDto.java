package com.korit.projectrrs.dto.walkingRecord.request;

import com.korit.projectrrs.entity.PetProfile;
import com.korit.projectrrs.entity.WalkingRecordAttachment;
import com.korit.projectrrs.entity.WalkingRecordWeatherState;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class WalkingRecordRequestDto {
    @NotBlank
    private WalkingRecordWeatherState walkingRecordWeatherState;
    @NotBlank
    private Integer walkingRecordDistance;
    @NotBlank
    private Integer walkingRecordWalkingHours;
    @NotBlank
    private Integer walkingRecordWalkingMinutes;
    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate walkingRecordCreateAt;
    private String walkingRecordMemo;
    private List<MultipartFile> files;
}
