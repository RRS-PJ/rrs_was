package com.korit.projectrrs.service;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.todo.request.TodoCreateRequestDto;
import com.korit.projectrrs.dto.todo.response.TodoCreateReponseDto;
import com.korit.projectrrs.dto.todo.response.TodoGetResponseDto;
import com.korit.projectrrs.dto.todo.response.TodoUpdateResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TodoService {

    ResponseDto<TodoCreateReponseDto> createTodo(Long id, TodoCreateRequestDto dto);

    ResponseDto<List<TodoGetResponseDto>> getAllTodo(Long id);

    ResponseDto<TodoGetResponseDto> getRecentTodo(Long id, Long todoId);

    ResponseDto<TodoUpdateResponseDto> updateTodo(TodoCreateRequestDto dto);

    ResponseDto<Void> deleteTodo(Long id, Long todoId);
}
