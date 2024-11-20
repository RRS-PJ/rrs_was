package com.korit.projectrrs.dto.todo.response;

import com.korit.projectrrs.entity.Todo;

import java.util.Date;

public class TodoGetResponseDto {
    Long todoId;
    String todoPreparationContent;
    Date todoCreateAt;

    public TodoGetResponseDto(Todo createdTodo) {
    }
}
