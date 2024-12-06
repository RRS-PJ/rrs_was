package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.todo.request.TodoRequestDto;
import com.korit.projectrrs.dto.todo.response.TodoGetResponseDto;
import com.korit.projectrrs.dto.todo.response.TodoPostResponseDto;
import com.korit.projectrrs.dto.todo.response.TodoUpdateResponseDto;
import com.korit.projectrrs.security.PrincipalUser;

import java.time.LocalDate;
import java.util.List;

public interface TodoService {
    ResponseDto<TodoPostResponseDto> createTodo(PrincipalUser principalUser, TodoRequestDto dto);
    ResponseDto<List<TodoGetResponseDto>> getAllTodosByUserIdAndDay(PrincipalUser principalUser, LocalDate day);
    ResponseDto<TodoUpdateResponseDto> updateTodo(Long todoId,TodoUpdateRequestDto dto);
    ResponseDto<Void> deleteTodo(PrincipalUser principalUser, Long todoId);
}