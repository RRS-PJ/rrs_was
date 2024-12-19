package com.korit.projectrrs.dto.todo.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateTodoRequestDto {
    @Size(max = 255)
    private String todoPreparationContent;

    private LocalDate todoCreateAt;

    private Character todoStatus;
}
