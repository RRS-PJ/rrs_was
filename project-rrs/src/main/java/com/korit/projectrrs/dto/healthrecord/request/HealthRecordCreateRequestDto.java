package com.korit.projectrrs.dto.healthrecord.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Data
public class HealthRecordCreateRequestDto {
    @NotNull(message = "Weight is required.")
    private Double weight;

    @NotNull(message = "Pet Age is required.")
    private Short petAge;

    @NotBlank(message = "Abnormal Symptoms are required.")
    private String abnormalSymptoms;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    private String memo;

    // 업로드할 파일 목록을 저장하는 필드
    private List<MultipartFile> attachments;

    // 파일 목록을 반환하는 메서드
    public List<MultipartFile> getFiles() {
        return attachments;
    }
}
