package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.todo.request.TodoPostRequestDto;
import com.korit.projectrrs.dto.todo.request.TodoUpdateRequestDto;
import com.korit.projectrrs.dto.todo.response.TodoGetResponseDto;
import com.korit.projectrrs.dto.todo.response.TodoPostResponseDto;
import com.korit.projectrrs.dto.todo.response.TodoUpdateResponseDto;
import com.korit.projectrrs.security.PrincipalUser;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;

public interface TodoService {
    ResponseDto<TodoPostResponseDto> createTodo(PrincipalUser principalUser, @Valid TodoPostRequestDto dto);
    ResponseDto<List<TodoGetResponseDto>> getAllTodosByUserIdAndDay(PrincipalUser principalUser, LocalDate day);
    ResponseDto<TodoUpdateResponseDto> updateTodo(PrincipalUser principalUser, Long todoId,TodoUpdateRequestDto dto);
    ResponseDto<Void> deleteTodo(PrincipalUser principalUser, Long todoId);
}