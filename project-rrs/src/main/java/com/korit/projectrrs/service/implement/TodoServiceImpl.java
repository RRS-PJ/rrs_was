package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.common.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.todo.request.TodoRequestDto;
import com.korit.projectrrs.dto.todo.request.UpdateTodoRequestDto;
import com.korit.projectrrs.dto.todo.response.TodoResponseDto;
import com.korit.projectrrs.entity.Todo;
import com.korit.projectrrs.entity.User;
import com.korit.projectrrs.repositoiry.TodoRepository;
import com.korit.projectrrs.repositoiry.UserRepository;
import com.korit.projectrrs.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.InternalException;
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
                    .todoStatus('0')
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
    public ResponseDto<List<TodoResponseDto>> getAllTodosByUser(Long userId) {
        List<TodoResponseDto> data = null;
        try {
            List<Todo> optionalTodos = todoRepository.findAllByUser_UserId(userId);
            if (!optionalTodos.isEmpty()) {
                data = optionalTodos.stream()
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
    public ResponseDto<List<TodoResponseDto>> getAllTodosByUserIdAndDay(Long userId,LocalDate day) {
        List<TodoResponseDto> data = null;
        try {
            List<Todo> optionalTodos = todoRepository.findTodosByUserIdAndDay(userId, day);
            if (!optionalTodos.isEmpty()) {
                data = optionalTodos.stream()
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
    public ResponseDto<TodoResponseDto> updateTodo(Long userId, Long todoId, @Valid UpdateTodoRequestDto dto) {
        TodoResponseDto data = null;
        String todoContent = dto.getTodoPreparationContent();
        LocalDate todoCreateAt = dto.getTodoCreateAt();
        Character todoStatus = dto.getTodoStatus();

        if (todoContent != null){
            if (todoContent.length() > 255) {
                // Todo 내용 글자수 초과
                return ResponseDto.setFailed(ResponseMessage.TODO_TOO_LONG);
            }
        }

        if (todoStatus != null) {
            if (todoStatus != '0' && todoStatus != '1') {
            return ResponseDto.setFailed(ResponseMessage.TODO_NOT_EXIST_STATUS);
        }}

        try {
           Todo todo = todoRepository.findById(todoId)
                   .orElseThrow(() -> new InternalException(ResponseMessage.NOT_EXIST_TODO));

            if (!todo.getUser().getUserId().equals(userId)) {
                return ResponseDto.setFailed(ResponseMessage.NOT_MATCH_USER_ID);
            }

            if (!todo.getTodoId().equals(todoId)){
                return ResponseDto.setFailed(ResponseMessage.NOT_MATCH_TODO_ID);
            }


            Todo updatedTodo = todo.toBuilder()
                    .todoPreparationContent(todoContent == null ? todo.getTodoPreparationContent() : todoContent)
                    .todoCreateAt(todoCreateAt == null ? todo.getTodoCreateAt() : todoCreateAt)
                    .todoStatus(todoStatus == null ? todo.getTodoStatus() : todoStatus)
                    .build();

            todoRepository.save(updatedTodo);

            data = new TodoResponseDto(updatedTodo);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<Void> deleteTodo(Long userId , Long todoId) {
        try {
            Todo todo = todoRepository.findById(todoId)
                    .orElseThrow(() -> new InternalException(ResponseMessage.NOT_EXIST_TODO));

            if (!todo.getUser().getUserId().equals(userId)) {
                return ResponseDto.setFailed(ResponseMessage.NOT_MATCH_USER_ID);
            }

            todoRepository.deleteById(todoId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_TODO);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }
}