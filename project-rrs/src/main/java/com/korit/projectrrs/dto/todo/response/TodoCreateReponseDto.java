package com.korit.projectrrs.dto.todo.response;

import com.korit.projectrrs.entity.Todo;

import java.time.LocalDateTime;

public class TodoCreateReponseDto {
    Long todoId;
    String todoPreparationContent;
    LocalDateTime todoCreateAt;

    public TodoCreateReponseDto(Todo todo) {
        this.todoId = todo.getTodoId();
        this.todoPreparationContent = todo.getTodoPreparationContent();
        this.todoCreateAt = todo.getTodoCreateAt();
    }
}
