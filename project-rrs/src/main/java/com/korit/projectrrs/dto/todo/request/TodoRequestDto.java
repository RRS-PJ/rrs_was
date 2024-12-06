package com.korit.projectrrs.dto.todo.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TodoRequestDto {

    @NotBlank
    @Size(max = 255)
    private String todoPreparationContent;

    @NotNull
    private LocalDate todoCreateAt;
}
