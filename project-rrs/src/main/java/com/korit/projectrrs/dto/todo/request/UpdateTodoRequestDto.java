package com.korit.projectrrs.dto.todo.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateTodoRequestDto {
    @NotBlank
    @Size(max = 255)
    private String todoPreparationContent;

    private LocalDate todoCreateAt;
}
