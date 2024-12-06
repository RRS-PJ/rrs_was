package com.korit.projectrrs.service;

import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;

public interface TodoService {
    ResponseDto<TodoPostResponseDto> createTodo(String userId, @Valid TodoPostRequestDto dto);
    ResponseDto<List<TodoGetResponseDto>> getAllTodosByUserIdAndDay(String userId, LocalDate day);
    ResponseDto<TodoUpdateResponseDto> updateTodo(Long todoId, TodoUpdateRequestDto dto);
    ResponseDto<Void> deleteTodo(String userId, Long todoId);
}