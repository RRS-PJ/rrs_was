package com.korit.projectrrs.dto.todo.response;

import com.korit.projectrrs.entity.Todo;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TodoResponseDto {
    private Long todoId;
    private String todoPreparationContent;
    private LocalDate todoCreateAt;
    private Character todoStatus;

    public TodoResponseDto(Todo todo){
        this.todoId = todo.getTodoId();
        this.todoPreparationContent = todo.getTodoPreparationContent();
        this.todoCreateAt = todo.getTodoCreateAt();
        this.todoStatus = todo.getTodoStatus();
    }
}
