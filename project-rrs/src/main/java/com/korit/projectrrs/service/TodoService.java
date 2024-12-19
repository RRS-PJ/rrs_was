package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.todo.request.TodoRequestDto;
import com.korit.projectrrs.dto.todo.request.UpdateTodoRequestDto;
import com.korit.projectrrs.dto.todo.response.TodoResponseDto;
import com.korit.projectrrs.security.PrincipalUser;

import java.time.LocalDate;
import java.util.List;

public interface TodoService {
    ResponseDto<TodoResponseDto> createTodo(Long userId, TodoRequestDto dto);
    ResponseDto<List<TodoResponseDto>> getAllTodosByUserIdAndDay(Long userId, LocalDate day);
    ResponseDto<TodoResponseDto> updateTodo(Long userId, Long todoId, UpdateTodoRequestDto dto);
    ResponseDto<Void> deleteTodo(Long userId, Long todoId);
}