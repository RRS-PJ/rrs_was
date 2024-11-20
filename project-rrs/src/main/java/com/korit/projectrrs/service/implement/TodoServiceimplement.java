package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.common.ResponseMessage;
import com.korit.projectrrs.dto.ResponseDto;
import com.korit.projectrrs.dto.todo.request.TodoCreateRequestDto;
import com.korit.projectrrs.dto.todo.response.TodoCreateReponseDto;
import com.korit.projectrrs.dto.todo.response.TodoGetResponseDto;
import com.korit.projectrrs.dto.todo.response.TodoUpdateResponseDto;
import com.korit.projectrrs.entity.Todo;
import com.korit.projectrrs.repositoiry.TodoRepository;
import com.korit.projectrrs.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceimplement implements TodoService {
    private final TodoRepository todoRepository;

    @Override
    public ResponseDto<TodoCreateReponseDto> createTodo(Long id, TodoCreateRequestDto dto) {
        TodoCreateReponseDto data = null;
        String todoContetnt = dto.getTodoPreparationContent();

        if(todoContetnt == null || todoContetnt.isEmpty()){
            // 공백
            ResponseDto.setFailed(ResponseMessage.BAD_REQUEST);
        }

        if(todoContetnt.length() > 20){
            // 글자 수 초과
            ResponseDto.setFailed(ResponseMessage.BAD_REQUEST);
        }

        try {
            Todo createdTodo = Todo.builder()
                    .todoPreparationContent(todoContetnt)
                    .build();
            todoRepository.save(createdTodo);
            data = new TodoCreateReponseDto(createdTodo);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<List<TodoGetResponseDto>> getAllTodo(Long id) {
        TodoGetResponseDto data = null;
//        todoRepository.findById(id);
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
