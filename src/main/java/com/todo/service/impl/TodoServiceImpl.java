package com.todo.service.impl;

import com.google.common.base.Strings;
import com.todo.daos.TodoDao;
import com.todo.entities.Todo;
import com.todo.enums.TodoStatus;
import com.todo.models.TodoModel;
import com.todo.services.TodoService;
import com.todo.utils.DateTimeConverter;
import com.todo.utils.EntityConverter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Arrays;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoDao todoDao;

    @Autowired
    private EntityConverter entityConverter;

    @Autowired
    private DateTimeConverter dateTimeConverter;

    @Override
    public TodoModel createEvent(TodoModel todoModel) {
        Todo todo;
        if (todoModel.getId() != null) {
            todo = getOne(todoModel.getId());
            if (todo != null) {
                if (!Strings.isNullOrEmpty(todoModel.getTitle())) {
                    todo.setTitle(todoModel.getTitle());
                }
                todo.setDescription(todoModel.getDescription());
                if (!Strings.isNullOrEmpty(todoModel.getCreatedDate())) {
                    todo.setCreatedDate(dateTimeConverter.stringToCalender(todoModel.getCreatedDate()));
                }
            }
        } else {
            todo = new Todo();
            todo.setTitle(todoModel.getTitle());
            todo.setDescription(todoModel.getDescription());
            todo.setTodoStatus(TodoStatus.NEW);
            todo.setCreatedDate(dateTimeConverter.stringToCalender(todoModel.getCreatedDate()));
        }
        todo = todoDao.save(todo);
        if (todo != null) {
            return entityConverter.convertTodoEntity(todo);
        }
        return null;
    }

    @Override
    public List<TodoModel> deleteEvents(String ids) {
        List<Todo> todoListToUpdate = new ArrayList<>();
        String[] arrayId = ids.split("\\,");
        List<Long> deleteIdList = Arrays.stream(arrayId).map(item -> Long.parseLong(item)).collect(Collectors.toList());
        List<Todo> todoList = todoDao.findByIdIn(deleteIdList);
        todoList.forEach((event) -> {
            event.setTodoStatus(TodoStatus.DELETE);
            todoListToUpdate.add(event);
        });
        todoList.clear();
        todoList = todoDao.saveAll(todoListToUpdate);
        return todoList.stream().map((todo) -> entityConverter.convertTodoEntity(todo)).collect(Collectors.toList());
    }

    @Override
    public Page<Todo> searchEvents(String keyword, int pageId, int limit) {
        List<TodoStatus> tStatus = new ArrayList<>();
        tStatus.add(TodoStatus.NEW);
        Page<Todo> list = null;
        for (int i = 0; i < pageId; i++) {
            list = todoDao.searchEvents(keyword, tStatus, PageRequest.of(i, limit, Sort.by("id").ascending()));
        }
        return list;
    }

    @Override
    public TodoModel getById(Long eventId) {
        Todo todo = todoDao.getOne(eventId);
        if (todo != null) {
            return entityConverter.convertTodoEntity(todoDao.getOne(eventId));
        }
        return null;
    }

    private Todo getOne(Long id) {
        return todoDao.getOne(id);
    }
}
