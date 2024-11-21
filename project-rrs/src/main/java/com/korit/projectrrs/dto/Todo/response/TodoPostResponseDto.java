package com.korit.projectrrs.dto.Todo.response;

import com.korit.projectrrs.entity.Todo;

import java.time.LocalDate;

public class TodoPostResponseDto {
    Long todoId;
    String todoPreparationContent;
    LocalDate todoCreateAt;

    public TodoPostResponseDto(Todo todo) {
        this.todoId = todo.getTodoId();
        this.todoPreparationContent = todo.getTodoPreparationContent();
        this.todoCreateAt = todo.getTodoCreateAt();
    }
}
