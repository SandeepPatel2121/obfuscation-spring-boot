package com.todo.utils;

import com.todo.entities.Todo;
import com.todo.models.TodoModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class EntityConverter {
    
    @Autowired
    private DateTimeConverter dateTimeConverter;
    
    public TodoModel convertTodoEntity(Todo todo) {
        TodoModel todoModel = new TodoModel();
        
        todoModel.setId(todo.getId());
        todoModel.setTitle(todo.getTitle());
        todoModel.setDescription(todo.getDescription());
        todoModel.setTodoStatus(todo.getTodoStatus().toString());
        todoModel.setFormatedDate(todo.getCreatedDate());
        todoModel.setCreatedDate(dateTimeConverter.calenderToString(todo.getCreatedDate()));
        
        return todoModel;
    }
    
}
