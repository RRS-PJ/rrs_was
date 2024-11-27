package com.korit.projectrrs.dto.todo.response;

import com.korit.projectrrs.entity.Todo;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoPostResponseDto {
    private String todoPreparationContent;
    private LocalDateTime todoCreateAt;

    public TodoPostResponseDto(Todo todo) {
        this.todoPreparationContent = todo.getTodoPreparationContent();
        this.todoCreateAt = todo.getTodoCreateAt();
    }
}
