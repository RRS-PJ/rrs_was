package com.korit.projectrrs.dto.todo.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TodoPostRequestDto {
    private String todoPreparationContent;
    private LocalDate todoCreateAt;
}
