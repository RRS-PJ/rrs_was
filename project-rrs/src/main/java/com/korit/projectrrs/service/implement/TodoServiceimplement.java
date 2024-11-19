package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.todo.request.TodoCreateRequestDto;
import com.korit.projectrrs.dto.todo.response.TodoCreateReponseDto;
import com.korit.projectrrs.dto.todo.response.TodoGetResponseDto;
import com.korit.projectrrs.dto.todo.response.TodoUpdateResponseDto;
import com.korit.projectrrs.service.TodoService;

public class TodoServiceimplement implements TodoService {
    @Override
    public ResponseDto<TodoCreateReponseDto> createTodo(TodoCreateRequestDto dto) {
        return null;
    }

    @Override
    public ResponseDto<TodoGetResponseDto> getAllTodo(Long id) {
        return null;
    }

    @Override
    public ResponseDto<TodoGetResponseDto> getRecentTodo(Long id, Long todoId) {
        return null;
    }

    @Override
    public ResponseDto<TodoUpdateResponseDto> updateTodo(TodoCreateRequestDto dto) {
        return null;
    }

    @Override
    public ResponseDto<Void> deleteTodo(Long id, Long todoId) {
        return null;
    }
}
