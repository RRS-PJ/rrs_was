package com.korit.projectrrs.controller;

import com.korit.projectrrs.common.constant.ApiMappingPattern;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.todo.request.TodoRequestDto;
import com.korit.projectrrs.dto.todo.request.UpdateTodoRequestDto;
import com.korit.projectrrs.dto.todo.response.TodoResponseDto;
import com.korit.projectrrs.security.PrincipalUser;
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

    private final String TODO_CREATE = "/write";
    private final String TODO_GET = "";
    private final String TODO_GET_BY_DAY = "/day";
    private final String TODO_UPDATE = "/{todoId}";
    private final String TODO_DELETE = "/{todoId}";

    @PostMapping(TODO_CREATE)
    private ResponseEntity<ResponseDto<TodoResponseDto>> createTodo(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @Valid @RequestBody TodoRequestDto dto
    ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<TodoResponseDto> response = todoService.createTodo(userId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(TODO_GET)
    private ResponseEntity<ResponseDto<List<TodoResponseDto>>> getAllTodosByUserId(
            @AuthenticationPrincipal PrincipalUser principalUser
    ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<List<TodoResponseDto>> response = todoService.getAllTodosByUser(userId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(TODO_GET_BY_DAY)
    private ResponseEntity<ResponseDto<List<TodoResponseDto>>> getAllTodosByUserIdAndDay(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestParam LocalDate day
    ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<List<TodoResponseDto>> response = todoService.getAllTodosByUserIdAndDay(userId, day);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping(TODO_UPDATE)
    private ResponseEntity<ResponseDto<TodoResponseDto>> updateTodo(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long todoId,
            @Valid @RequestBody UpdateTodoRequestDto dto
    ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<TodoResponseDto> response = todoService.updateTodo(userId, todoId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping(TODO_DELETE)
    private ResponseEntity<ResponseDto<Void>> deleteTodo(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @PathVariable Long todoId
    ) {
        Long userId = principalUser.getUser().getUserId();
        ResponseDto<Void> response = todoService.deleteTodo(userId, todoId);
        HttpStatus status = response.isResult() ? HttpStatus.NO_CONTENT : HttpStatus.FORBIDDEN;
        return ResponseEntity.status(status).body(response);
    }
}