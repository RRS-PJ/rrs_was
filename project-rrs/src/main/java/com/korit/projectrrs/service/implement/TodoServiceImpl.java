package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.common.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.Todo.request.TodoPostRequestDto;
import com.korit.projectrrs.dto.Todo.response.TodoPostResponseDto;
import com.korit.projectrrs.entity.Todo;
import com.korit.projectrrs.entity.User;
import com.korit.projectrrs.service.TodoService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {
    @Override
    public ResponseDto<TodoPostResponseDto> createTodo(String userId, TodoPostRequestDto dto) {
        TodoPostRequestDto data = null;
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
            data =  new TodoPostRequestDto(todo);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);

        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
