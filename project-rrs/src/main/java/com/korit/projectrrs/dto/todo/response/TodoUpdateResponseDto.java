package com.korit.projectrrs.dto.todo.response;

import com.korit.projectrrs.entity.Todo;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TodoUpdateResponseDto {
    private Long todoId;
    private String todoPreparationContent;
    private LocalDate todoCreateAt;

    public TodoUpdateResponseDto(Todo todo){
        this.todoId = todo.getTodoId();
        this.todoPreparationContent = todo.getTodoPreparationContent();
        this.todoCreateAt = todo.getTodoCreateAt();
    }
}
