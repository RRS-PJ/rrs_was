package com.korit.projectrrs.repositoiry;

import com.korit.projectrrs.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
//
//    @Query("SELECT t FROM Todo t WHERE t.user.id = :userId " +
//            "AND FUNCTION('DATE', t.todoCreateAt) = :day " +
//            "ORDER BY t.todoCreateAt ASC")
//    List<Todo> findTodosByUserIdAndDay(@Param("userId") Long userId, @Param("day") LocalDate day);
}
