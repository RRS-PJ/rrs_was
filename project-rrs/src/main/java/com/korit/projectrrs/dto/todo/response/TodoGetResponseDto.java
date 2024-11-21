package com.korit.projectrrs.dto.todo.response;

import com.korit.projectrrs.entity.Todo;

import java.time.LocalDate;

public class TodoGetResponseDto {
    Long todoId;
    String todoPreparationContent;
    LocalDate todoCreateAt;

    public TodoGetResponseDto(Todo todo) {
        this.todoId = todo.getTodoId();
        this.todoPreparationContent = todo.getTodoPreparationContent();
        this.todoCreateAt = todo.getTodoCreateAt();
    }
}
