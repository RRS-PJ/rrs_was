package com.korit.projectrrs.dto.todo.response;

import com.korit.projectrrs.entity.Todo;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TodoGetResponseDto {
    private String todoPreparationContent;
    private LocalDate todoCreateAt;

    public TodoGetResponseDto(Todo todo) {
        this.todoPreparationContent = todo.getTodoPreparationContent();
        this.todoCreateAt = todo.getTodoCreateAt();
    }
}
