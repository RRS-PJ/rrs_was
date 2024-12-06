package com.korit.projectrrs.dto.todo.response;

import com.korit.projectrrs.entity.Todo;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TodoPostResponseDto {
    private String todoPreparationContent;
    private LocalDate todoCreateAt;

    public TodoPostResponseDto(Todo todo){
        this.todoPreparationContent = todo.getTodoPreparationContent();
        this.todoCreateAt = todo.getTodoCreateAt();
    }
}