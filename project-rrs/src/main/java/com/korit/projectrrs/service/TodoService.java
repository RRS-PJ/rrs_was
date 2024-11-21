package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.todo.request.TodoPostRequestDto;
import com.korit.projectrrs.dto.todo.response.TodoPostResponseDto;
import jakarta.validation.Valid;

public interface TodoService {
    ResponseDto<TodoPostResponseDto> createTodo(String userId, @Valid TodoPostRequestDto dto);
}
