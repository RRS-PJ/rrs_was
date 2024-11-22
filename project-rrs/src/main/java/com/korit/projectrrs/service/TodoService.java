package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.todo.request.TodoPostRequestDto;
import com.korit.projectrrs.dto.todo.request.TodoUpdateRequestDto;
import com.korit.projectrrs.dto.todo.response.TodoGetResponseDto;
import com.korit.projectrrs.dto.todo.response.TodoPostResponseDto;
import com.korit.projectrrs.dto.todo.response.TodoUpdateResponseDto;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;

public interface TodoService {
    ResponseDto<TodoPostResponseDto> createTodo(String userId, @Valid TodoPostRequestDto dto);

    ResponseDto<List<TodoGetResponseDto>> getAllTodosByUserIdAndDay(String userId, LocalDate day);

    ResponseDto<TodoUpdateResponseDto> updateTodo(String userId, Long todoId, TodoUpdateRequestDto dto);

    ResponseDto<Void> deleteTodo(String userId, Long todoId);
}
