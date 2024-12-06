package com.korit.projectrrs.controller;

import com.korit.projectrrs.dto.todo.request.TodoPostRequestDto;
import com.korit.projectrrs.dto.todo.request.TodoUpdateRequestDto;
import com.korit.projectrrs.dto.todo.response.TodoGetResponseDto;
import com.korit.projectrrs.dto.todo.response.TodoPostResponseDto;
import com.korit.projectrrs.dto.todo.response.TodoUpdateResponseDto;
import com.korit.projectrrs.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.TODO)
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    private final String TODO_GET = "";
    private final String TODO_UPDATE = "/{todoId}";
    private final String TODO_DELETE = "/{todoId}";

    @PostMapping
    private ResponseEntity<ResponseDto<TodoPostResponseDto>> createTodo(
            @AuthenticationPrincipal String userId,
            @Valid @RequestBody TodoPostRequestDto dto
    ) {
        ResponseDto<TodoPostResponseDto> response = todoService.createTodo(userId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(TODO_GET)
    private ResponseEntity<ResponseDto<List<TodoGetResponseDto>>> getAllTodosByUserIdAndDay(
            @AuthenticationPrincipal String userId,
            @RequestParam LocalDate day
    ) {
        ResponseDto<List<TodoGetResponseDto>> response = todoService.getAllTodosByUserIdAndDay(userId, day);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping(TODO_UPDATE)
    private ResponseEntity<ResponseDto<TodoUpdateResponseDto>> updateTodo(
            @AuthenticationPrincipal String userId,
            @PathVariable Long todoId,
            @RequestBody TodoUpdateRequestDto dto
    ) {
        ResponseDto<TodoUpdateResponseDto> response = todoService.updateTodo(todoId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping(TODO_DELETE)
    private ResponseEntity<ResponseDto<Void>> deleteTodo(
            @AuthenticationPrincipal String userId,
            @PathVariable Long todoId
    ) {
        ResponseDto<Void> response = todoService.deleteTodo(userId, todoId);
        HttpStatus status = response.isResult() ? HttpStatus.NO_CONTENT : HttpStatus.FORBIDDEN;
        return ResponseEntity.status(status).body(response);
    }
}