package com.korit.projectrrs.dto.todo.response;

import java.util.Date;

public class TodoCreateReponseDto {
    Long todoId;
    String todoPreparationContent;
    Date todoCreateAt = new Date();
}
