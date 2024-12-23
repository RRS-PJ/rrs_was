package com.korit.projectrrs.repositoiry;

import com.korit.projectrrs.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    @Query(value = """
    SELECT 
        T.*
    FROM
        USERS U
    JOIN
        TODOS T ON U.USER_ID = T.USER_ID
    WHERE
        U.USER_ID = :userId
        AND T.TODO_CREATE_AT = :day
    """, nativeQuery = true)
    List<Todo> findTodosByUserIdAndDay(@Param("userId") Long userId,@Param("day") LocalDate day);

    List<Todo> findAllByUser_UserId(Long userId);
}