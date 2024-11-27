package com.korit.projectrrs.dto.walkingRecord.request;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.korit.projectrrs.entity.PetProfile;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class WalkingRecordRequestDto {
    @NotBlank
    private PetProfile petProfile;
    @NotBlank
    private Character walkingRecordWeatherState;
    @NotBlank
    private int walkingRecordDistance;
    @NotBlank
    private LocalTime walkingRecordWalkingTime;
    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime walkingRecordCreateAt;

    private String walkingRecordMemo;

    private String walkingRecordImageUrl;
}
