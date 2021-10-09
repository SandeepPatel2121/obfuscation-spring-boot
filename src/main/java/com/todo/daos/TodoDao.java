package com.todo.daos;

import com.todo.entities.Todo;
import com.todo.enums.TodoStatus;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface TodoDao extends JpaRepository<Todo, Long>, JpaSpecificationExecutor<Todo>, PagingAndSortingRepository<Todo, Long> {

    List<Todo> findByTodoStatusIn(List<TodoStatus> todoStatusList);

    List<Todo> findByIdIn(List<Long> eventIdList);

    Page<Todo> findByTodoStatusIn(List<TodoStatus> todoStatusList, Pageable pageable);

    @Query(value = "SELECT * from Todo where (title like %:searchText% or description like %:searchText%) AND todo_status IN :status", nativeQuery = true)
    Page<Todo> searchEvents(@Param(value = "searchText") String search, @Param(value = "status") List<TodoStatus> status, Pageable pageable);

}
