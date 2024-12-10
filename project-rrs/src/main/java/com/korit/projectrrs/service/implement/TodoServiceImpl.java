package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.common.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.todo.request.TodoRequestDto;
import com.korit.projectrrs.dto.todo.response.TodoResponseDto;
import com.korit.projectrrs.entity.Todo;
import com.korit.projectrrs.entity.User;
import com.korit.projectrrs.repositoiry.TodoRepository;
import com.korit.projectrrs.repositoiry.UserRepository;
import com.korit.projectrrs.security.PrincipalUser;
import com.korit.projectrrs.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseDto<TodoResponseDto> createTodo(Long userId, @Valid TodoRequestDto dto) {
        TodoResponseDto data = null;
        String todoContent = dto.getTodoPreparationContent();
        LocalDate todoCreateAt = dto.getTodoCreateAt();

        if (todoContent == null || todoContent.trim().isEmpty()) {
            // Todo 내용 공백
            return ResponseDto.setFailed(ResponseMessage.TODO_IS_EMPTY);
        }

        if (todoContent.length() > 255) {
            // Todo 내용 글자수 초과
            return ResponseDto.setFailed(ResponseMessage.TODO_TOO_LONG);
        }

        try {
            Optional<User> optionalUser = userRepository.findById(userId);
            if (optionalUser.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);
            }
            User user = optionalUser.get();
            Todo todo = Todo.builder()
                    .user(user)
                    .todoPreparationContent(todoContent)
                    .todoCreateAt(todoCreateAt)
                    .build();

            todoRepository.save(todo);
            data = new TodoResponseDto(todo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<List<TodoResponseDto>> getAllTodosByUserIdAndDay(Long userId,LocalDate day) {
        List<TodoResponseDto> data = null;
        try {
            Optional<List<Todo>> optionaltodos = todoRepository.findTodosByUserIdAndDay(userId, day);
            if (optionaltodos.isPresent()) {
                List<Todo> todos = optionaltodos.get();
                data = todos.stream()
                        .map(TodoResponseDto::new)
                        .collect(Collectors.toList());
            } else {
                return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<TodoResponseDto> updateTodo(Long todoId, @Valid TodoRequestDto dto) {
        TodoResponseDto data = null;
        String todoContent = dto.getTodoPreparationContent();

        if (todoContent == null || todoContent.trim().isEmpty()) {
            // Todo 내용 공백
            return ResponseDto.setFailed(ResponseMessage.TODO_IS_EMPTY);
        }

        if (todoContent.length() > 255) {
            // Todo 내용 글자수 초과
            return ResponseDto.setFailed(ResponseMessage.TODO_TOO_LONG);
        }

        try {
            Optional<Todo> optionalTodo = todoRepository.findById(todoId);
            if(optionalTodo.isEmpty()) ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);

            Todo updateTodo = optionalTodo.get();

            updateTodo = Todo.builder()
                    .user(updateTodo.getUser())
                    .todoId(todoId)
                    .todoPreparationContent(dto.getTodoPreparationContent())
                    .todoCreateAt(updateTodo.getTodoCreateAt())
                    .build();

            todoRepository.save(updateTodo);

            data = new TodoResponseDto(updateTodo);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<Void> deleteTodo(Long todoId) {
        try {
            if(!todoRepository.existsById(todoId)) ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
            todoRepository.deleteById(todoId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }
}