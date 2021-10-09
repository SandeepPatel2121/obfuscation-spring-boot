package com.todo.services;

import com.todo.entities.Todo;
import com.todo.models.TodoModel;
import java.util.List;
import org.springframework.data.domain.Page;

public interface TodoService {

    TodoModel createEvent(TodoModel todoModel);

    List<TodoModel> deleteEvents(String id);

    Page<Todo> searchEvents(String keyword, int pageId, int limit);

    TodoModel getById(Long eventId);

}
