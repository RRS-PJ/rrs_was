package com.korit.projectrrs.dto.healthrecord.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Data
public class HealthRecordUpdateRequestDto {
    @NotNull(message = "Weight is required.")
    private Double weight;

    @NotNull(message = "Pet Age is required.")
    private Short petAge;

    @NotBlank(message = "Abnormal Symptoms are required.")
    private String abnormalSymptoms;

    private String memo;

    private List<MultipartFile> attachments;

    public List<MultipartFile> getFiles() {
        return attachments;
    }
}
