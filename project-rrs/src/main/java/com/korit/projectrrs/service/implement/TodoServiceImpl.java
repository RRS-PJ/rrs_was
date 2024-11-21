package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.common.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.todo.request.TodoPostRequestDto;
import com.korit.projectrrs.dto.todo.response.TodoPostResponseDto;
import com.korit.projectrrs.entity.Todo;
import com.korit.projectrrs.entity.User;
import com.korit.projectrrs.repositoiry.TodoRepository;
import com.korit.projectrrs.repositoiry.UserRepository;
import com.korit.projectrrs.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseDto<TodoPostResponseDto> createTodo(String userId, TodoPostRequestDto dto) {
        TodoPostResponseDto data = null;
        String todoContent = dto.getTodoPreparationContent();

        if (todoContent == null || todoContent.trim().isEmpty()) {
            // 공백
            return ResponseDto.setFailed(ResponseMessage.BAD_REQUEST);
        }

        if(todoContent.length() > 255){
            // 글자 수 초과
            return ResponseDto.setFailed(ResponseMessage.BAD_REQUEST);
        }

        try {
            Optional<User> optionalUser = userRepository.findByUserId(userId);
            if (optionalUser.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER_ID);
            }
            User user = optionalUser.get();
            Todo todo = Todo.builder()
                    .user(user)
                    .todoPreparationContent(todoContent)
                    .todoCreateAt(LocalDate.now())
                    .build();
            todoRepository.save(todo);
            data =  new TodoPostResponseDto(todo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
